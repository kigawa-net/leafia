package net.kigawa.leafia.client.usecase.state

import net.kigawa.kodel.api.err.Res
import net.kigawa.kodel.api.err.err
import net.kigawa.leafia.client.domain.FirstSubCommandName
import net.kigawa.leafia.client.domain.StateCommandOptionName
import net.kigawa.leafia.client.domain.StateSubCommandName
import net.kigawa.leafia.client.domain.command.CommandErr
import net.kigawa.leafia.client.domain.command.CommandExecutor
import net.kigawa.leafia.client.domain.command.option.CommandOption
import net.kigawa.leafia.client.domain.command.option.CommandOptionName
import net.kigawa.leafia.client.domain.command.route.CommandRoute
import net.kigawa.leafia.client.domain.state.StateCommandRoutes
import net.kigawa.leafia.client.usecase.command.ParentCommandParser
import net.kigawa.leafia.client.usecase.command.RawCommandArgsParser
import net.kigawa.leafia.client.usecase.command.option.CommandOptionParser
import net.kigawa.leafia.client.usecase.state.receive.ReceiveCommandParser
import net.kigawa.leafia.client.usecase.state.send.SendCommandParser

class StateCommandParser(
    private val commandArgsParser: RawCommandArgsParser,
    private val stateCommandRoutes: StateCommandRoutes,
): ParentCommandParser<FirstSubCommandName, StateCommandOptionName, StateSubCommandName, CommandOptionName> {
    override val optionParser: CommandOptionParser<StateCommandOptionName> =
        StateCommandOptionParser(stateCommandRoutes)

    override fun parseSubCommand(
        commandRoute: CommandRoute<StateSubCommandName, *>?,
        args: List<String>,
        parsers: List<CommandOptionParser<*>>,
        values: List<String>,
        options: List<CommandOption>,
    ): Res<CommandExecutor, CommandErr> {
        if (commandRoute == null) return CommandErr("require sub command for state").err()
        return when (commandRoute.name) {
            StateSubCommandName.RECEIVE ->
                commandArgsParser.parse(
                    stateCommandRoutes.receive, args, parsers,
                    ReceiveCommandParser(stateCommandRoutes.receive)
                )
            StateSubCommandName.SEND ->
                commandArgsParser.parse(
                    stateCommandRoutes.send, args, parsers,
                    SendCommandParser(stateCommandRoutes.send)
                )
        }
    }

    override fun parseCommand(
        parsers: List<CommandOptionParser<*>>,
        values: List<String>,
        options: List<CommandOption>,
    ): Res<CommandExecutor, CommandErr> {
        return CommandErr("require sub command for state").err()
    }
}