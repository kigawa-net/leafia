package net.kigawa.leafia.client.usecase.command

import net.kigawa.kodel.api.err.Res
import net.kigawa.leafia.client.domain.command.CommandErr
import net.kigawa.leafia.client.domain.command.CommandExecutor
import net.kigawa.leafia.client.domain.command.option.CommandOptionName
import net.kigawa.leafia.client.usecase.command.option.CommandOptionParser

interface CommandParser<O: CommandOptionName> {
    val optionParser: CommandOptionParser<O>

    fun parseCommand(
        parsers: List<CommandOptionParser<*>>, values: List<String>,
    ): Res<CommandExecutor, CommandErr>
}