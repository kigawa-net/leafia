package net.kigawa.leafia.client.usecase.state.send

import net.kigawa.kodel.api.err.Res
import net.kigawa.kodel.api.err.err
import net.kigawa.kodel.api.err.ok
import net.kigawa.leafia.client.domain.SendCommandOptionName
import net.kigawa.leafia.client.domain.StateSubCommandName
import net.kigawa.leafia.client.domain.command.CommandErr
import net.kigawa.leafia.client.domain.command.CommandExecutor
import net.kigawa.leafia.client.domain.command.option.CommandOption
import net.kigawa.leafia.client.domain.command.route.CommandRoute
import net.kigawa.leafia.client.usecase.command.CommandParser
import net.kigawa.leafia.client.usecase.command.option.CommandOptionParser

class SendCommandParser(
    sendCommandDefine: CommandRoute<StateSubCommandName, SendCommandOptionName>,
): CommandParser<SendCommandOptionName> {
    override val optionParser: CommandOptionParser<SendCommandOptionName> =
        SendCommandOptionParser(sendCommandDefine)

    override fun parseCommand(
        parsers: List<CommandOptionParser<*>>,
        values: List<String>,
        options: List<CommandOption>,
    ): Res<CommandExecutor, CommandErr> {
        val server = values.getOrNull(0) ?: return CommandErr("require <server>").err()
        val path = values.getOrNull(1) ?: "leafia.yml"
        return SendCommandExecutor(server, path).ok()
    }
}