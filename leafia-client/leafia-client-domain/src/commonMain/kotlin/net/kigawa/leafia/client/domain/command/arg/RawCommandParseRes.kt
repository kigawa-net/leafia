package net.kigawa.leafia.client.domain.command.arg

import net.kigawa.leafia.client.domain.command.CommandExecutor
import net.kigawa.leafia.client.domain.command.option.CommandOption

data class RawCommandParseRes(
    val executor: CommandExecutor,
    val args: List<String>,
    val options: List<CommandOption>,
)