package net.kigawa.leafia.client.usecase.state.receive

import net.kigawa.kodel.api.err.Res
import net.kigawa.kodel.api.err.err
import net.kigawa.kodel.api.err.ok
import net.kigawa.leafia.client.domain.ReceiveCommandOptionName
import net.kigawa.leafia.client.domain.StateSubCommandName
import net.kigawa.leafia.client.domain.command.CommandErr
import net.kigawa.leafia.client.domain.command.CommandExecutor
import net.kigawa.leafia.client.domain.command.option.CommandOption
import net.kigawa.leafia.client.domain.command.route.CommandRoute
import net.kigawa.leafia.client.usecase.command.CommandParser
import net.kigawa.leafia.client.usecase.command.option.CommandOptionParser

class ReceiveCommandParser(
    receiveCommandDefine: CommandRoute<StateSubCommandName, ReceiveCommandOptionName>,
): CommandParser<ReceiveCommandOptionName> {
    override val optionParser: CommandOptionParser<ReceiveCommandOptionName> =
        ReceiveCommandOptionParser(receiveCommandDefine)

    override fun parseCommand(
        parsers: List<CommandOptionParser<*>>,
        values: List<String>,
        options: List<CommandOption>,
    ): Res<CommandExecutor, CommandErr> {
        val server = values.getOrNull(0) ?: return CommandErr("require <server>").err()
        val path = values.getOrNull(1) ?: "leafia.yml"
        return ReceiveCommandExecutor(server, path).ok()
    }
}