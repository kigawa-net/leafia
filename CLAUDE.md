# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

日本語で話す

## Build & Run

```bash
# Full build
./gradlew build

# Compile specific module
./gradlew :leafia-client:leafia-client-domain:compileKotlinJvm
./gradlew :leafia-client:leafia-client-usecase:compileKotlinJvm
./gradlew :leafia-client:compileKotlinJvm

# Run the CLI
./gradlew :leafia-client:jvmRun --args="push leafia.yml"
```

No tests exist yet. The common test source sets are configured but empty.

## Project Structure

Kotlin Multiplatform (JVM target) with these Gradle modules:

| Module | Purpose |
|---|---|
| `leafia-client:leafia-client-domain` | Domain models: command routes, option names, executors |
| `leafia-client:leafia-client-usecase` | Parsing orchestration, command parsers |
| `leafia-client` | App entry point, produces JVM executable |
| `leafia-server:leafia-server-domain` | Server skeleton (not yet implemented) |
| `leafia-prot:leafia-prot-domain` | Protocol skeleton (not yet implemented) |

`settings.gradle.kts` uses `includeIfExistsAndGroup` — modules are auto-included if their directory exists, making it easy to add new modules by creating the directory.

## Command Architecture

Parsing flows: `Main → App → LeafiaCliExecutor → RawCommandArgsParser → LeafiaCommandParser → (subcommand parser)`

**Core types (domain):**
- `CommandRoute<C, O>` — describes a command: its name type (`C: CommandName`), option type (`O: CommandOptionName`), required positional value count (`valueSize`), and option defines.
- `ParentCommandRoute<C, D, O>` — extends `CommandRoute` for commands with sub-commands; `D` is the sub-command name type. Extend this class and call `route { }` to register child routes.
- `NonParentCommandRoute<C, O>` — leaf command route, constructed directly.
- `CommandExecutor` — marker interface; returned from `parseCommand()` carrying parsed values for later execution.

**Core types (usecase):**
- `CommandParser<O>` — implements `parseCommand(parsers, values, options)` for leaf commands.
- `ParentCommandParser<C, O, D, P>` — extends `CommandParser`; implements `parseSubCommand(commandRoute, args, parsers, values, options)` to route to child parsers.
- `RawCommandArgsParser` — drives the parse loop: extracts options (args starting with `-`) via `RawCommandOptionParser`, consumes `valueSize` positional values, appends remaining args to `values`, then delegates to the command parser.
- `Res<T, E>` from kodel — success/error type; use `.ok()` / `.err()` extensions.

**Parsing conventions:**
- Options (flags/valued options) can appear anywhere before positional args are consumed.
- `valueSize = 0` on a route means no required positional values; any remaining non-option args are appended to `values` in `parseCommand`, enabling optional args with defaults (e.g., `path = values.getOrNull(0) ?: "leafia.yml"`).
- Required positional args use `valueSize = N`; optional trailing args are still accessible via index on the `values` list passed to `parseCommand`.

**Adding a new command:**
1. Add enum entry to `FirstSubCommandName` (or create a new sub-command enum for nested commands).
2. Add a `CommandOptionName` enum for the new command's options (empty enum if none).
3. Add a route via `route { NonParentCommandRoute(...) }` in `LeafiaCommandRoutes` (or a new `ParentCommandRoute` subclass for commands with sub-commands).
4. Create a `CommandOptionParser` and `CommandParser` in the usecase module.
5. Wire the new case in `LeafiaCommandParser.parseSubCommand`.

**Option defines:** Option name enums cannot implement `CommandOptionDefine` directly because Kotlin's `Enum.name: String` conflicts with `CommandOptionDefine.name: O`. Use companion object anonymous objects instead:
```kotlin
companion object {
    val MY_DEFINE: CommandOptionDefine<MyOptionName> = object: CommandOptionDefine<MyOptionName> {
        override val name = MY_OPTION
        override val valueSize = 0
    }
}
```

## System Architecture (CD Tool)

`push` コマンドのシーケンス（`state receive`/`state send` は job コンテナ内から呼ばれる）:

1. CLI が `leafia.yml` をサーバーに送信
2. CLI がファイルハッシュを計算・送信
3. サーバーが k8s に cache PVC を確保し、プロジェクトをロック
4. サーバーが差分ファイルリストを返す → CLI が変更ファイルを送信
5. サーバーが PVC にファイル差分を適用し、k8s Job を作成
6. Job が `state receive` でサーバーから `state.yml` を取得
7. Job が `deploy` を実行（実際のデプロイ）
8. Job が `state send` で更新した `state.yml` をサーバーに送信
9. サーバーが PVC ロックを解除

`CommandExecutor.execute()` がコマンドの実際の処理を担う。`Res<Unit, CommandErr>` を返し、エラーは `LeafiaCliExecutor` でスタックトレースを出力。

## Dependencies

External library: `net.kigawa.kodel:kodel-domain` and `net.kigawa.kodel:api` at version `5.0.1` (managed in `buildSrc/src/main/kotlin/Version.kt`). This library provides `CircularRouteGroup` (base for `ParentCommandRoute`) and `Res<T, E>`.

Kotlin compiler flag `-Xcontext-parameters` is enabled globally.