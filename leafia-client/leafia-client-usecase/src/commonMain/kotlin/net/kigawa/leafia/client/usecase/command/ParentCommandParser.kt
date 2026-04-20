package net.kigawa.leafia.client.usecase.command

import net.kigawa.kodel.api.err.Res
import net.kigawa.leafia.client.domain.command.CommandErr
import net.kigawa.leafia.client.domain.command.CommandExecutor
import net.kigawa.leafia.client.domain.command.define.CommandName
import net.kigawa.leafia.client.domain.command.option.CommandOption
import net.kigawa.leafia.client.domain.command.option.CommandOptionName
import net.kigawa.leafia.client.domain.command.route.CommandRoute
import net.kigawa.leafia.client.usecase.command.option.CommandOptionParser

interface ParentCommandParser<C: CommandName, O: CommandOptionName, D: CommandName, P: CommandOptionName>:
    CommandParser<O> {

    fun parseSubCommand(
        commandRoute: CommandRoute<D, *>?, args: List<String>, parsers: List<CommandOptionParser<*>>,
        values: List<String>, options: List<CommandOption>,
    ): Res<CommandExecutor, CommandErr>
}