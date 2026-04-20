package net.kigawa.leafia.client.usecase.state.receive

import net.kigawa.leafia.client.domain.ReceiveCommandOptionName
import net.kigawa.leafia.client.domain.StateSubCommandName
import net.kigawa.leafia.client.domain.command.option.CommandOption
import net.kigawa.leafia.client.domain.command.option.CommandOptionDefine
import net.kigawa.leafia.client.domain.command.route.CommandRoute
import net.kigawa.leafia.client.usecase.command.option.CommandOptionParser

class ReceiveCommandOptionParser(
    receiveCommandDefine: CommandRoute<StateSubCommandName, ReceiveCommandOptionName>,
): CommandOptionParser<ReceiveCommandOptionName> {
    override val commandOptionDefines: List<CommandOptionDefine<ReceiveCommandOptionName>> =
        receiveCommandDefine.commandOptionDefines
    override fun parse(commandOptionDefine: CommandOptionDefine<ReceiveCommandOptionName>, args: List<String>): CommandOption {
        throw UnsupportedOperationException()
    }
}