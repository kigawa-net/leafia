package net.kigawa.leafia.client.usecase.state.send

import net.kigawa.leafia.client.domain.SendCommandOptionName
import net.kigawa.leafia.client.domain.StateSubCommandName
import net.kigawa.leafia.client.domain.command.option.CommandOption
import net.kigawa.leafia.client.domain.command.option.CommandOptionDefine
import net.kigawa.leafia.client.domain.command.route.CommandRoute
import net.kigawa.leafia.client.usecase.command.option.CommandOptionParser

class SendCommandOptionParser(
    sendCommandDefine: CommandRoute<StateSubCommandName, SendCommandOptionName>,
): CommandOptionParser<SendCommandOptionName> {
    override val commandOptionDefines: List<CommandOptionDefine<SendCommandOptionName>> =
        sendCommandDefine.commandOptionDefines
    override fun parse(commandOptionDefine: CommandOptionDefine<SendCommandOptionName>, args: List<String>): CommandOption {
        throw UnsupportedOperationException()
    }
}