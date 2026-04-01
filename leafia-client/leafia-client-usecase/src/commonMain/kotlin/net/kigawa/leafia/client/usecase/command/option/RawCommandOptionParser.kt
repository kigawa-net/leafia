package net.kigawa.leafia.client.usecase.command.option

import net.kigawa.kodel.api.err.Res
import net.kigawa.kodel.api.err.err
import net.kigawa.kodel.api.err.ok
import net.kigawa.leafia.client.domain.command.CommandErr
import net.kigawa.leafia.client.domain.command.option.CommandOption
import net.kigawa.leafia.client.domain.command.option.CommandOptionDefine
import net.kigawa.leafia.client.domain.command.option.CommandOptionName

class RawCommandOptionParser {
    fun <T: CommandOptionDefine<O>, O: CommandOptionName> parse(
        prevArgs: List<String>, optionDefines: List<T>, parentOptionParsers: List<CommandOptionParser>,
        optionParsers: T.(values: List<String>) ->  CommandOptionParser,
    ): Res<Result, CommandErr> {
        val options = mutableListOf<CommandOption>()
        var args = prevArgs
        while (args.firstOrNull()?.startsWith("-") ?: false) {
            val option = args.first()
            val optionDefine = optionDefines
                .firstOrNull { it.name.isMatch(option) } ?: return CommandErr("unknown option $option").err()

            val optionValues = (0 until optionDefine.valueSize)
                .map { args.getOrNull(it) ?: return CommandErr("require option value").err() }
            args = args.drop(optionDefine.valueSize)
            options.add(optionParsers(optionDefine, optionValues))
        }
        return Result(options, args).ok()
    }
    data class Result(
        val options: List<CommandOption>,
        val args: List<String>,
    )
}