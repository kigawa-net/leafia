package net.kigawa.leafia.client.usecase.command.leafia

import net.kigawa.kodel.api.err.Res
import net.kigawa.kodel.api.err.err
import net.kigawa.leafia.client.domain.FirstSubCommandName
import net.kigawa.leafia.client.domain.command.CommandErr
import net.kigawa.leafia.client.domain.command.CommandExecutor
import net.kigawa.leafia.client.domain.command.option.CommandOptionName
import net.kigawa.leafia.client.domain.command.route.CommandRoute
import net.kigawa.leafia.client.domain.leafia.LeafiaCommandName
import net.kigawa.leafia.client.domain.leafia.LeafiaCommandOptionName
import net.kigawa.leafia.client.domain.leafia.LeafiaCommandRoutes
import net.kigawa.leafia.client.usecase.command.ParentCommandParser
import net.kigawa.leafia.client.usecase.command.RawCommandArgsParser
import net.kigawa.leafia.client.usecase.command.option.CommandOptionParser
import net.kigawa.leafia.client.usecase.push.PushCommandParser

class LeafiaCommandParser(
    private val commandArgsParser: RawCommandArgsParser,
    private val leafiaCommandRoutes: LeafiaCommandRoutes,
): ParentCommandParser<LeafiaCommandName, LeafiaCommandOptionName, FirstSubCommandName, CommandOptionName> {
    override fun parseSubCommand(
        commandRoute: CommandRoute<FirstSubCommandName, *>?,
        args: List<String>,
        parsers: List<CommandOptionParser<*>>,
        values: List<String>,
    ): Res<CommandExecutor, CommandErr> {
        if (commandRoute == null) return parseCommand(parsers, values)
        return when (commandRoute.name) {
            FirstSubCommandName.PUSH ->
                commandArgsParser.parse(
                    leafiaCommandRoutes.push, args, parsers,
                    PushCommandParser(leafiaCommandRoutes.push)
                )
        }
    }

    override val optionParser: CommandOptionParser<LeafiaCommandOptionName>
        get() = TODO("Not yet implemented")

    override fun parseCommand(
        parsers: List<CommandOptionParser<*>>,
        values: List<String>,
    ): Res<CommandExecutor, CommandErr> {
        return CommandErr("require sub command").err()
    }

}