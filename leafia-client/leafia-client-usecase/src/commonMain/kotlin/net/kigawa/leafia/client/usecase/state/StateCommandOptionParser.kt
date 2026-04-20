package net.kigawa.leafia.client.usecase.state

import net.kigawa.leafia.client.domain.FirstSubCommandName
import net.kigawa.leafia.client.domain.StateCommandOptionName
import net.kigawa.leafia.client.domain.command.option.CommandOption
import net.kigawa.leafia.client.domain.command.option.CommandOptionDefine
import net.kigawa.leafia.client.domain.command.route.CommandRoute
import net.kigawa.leafia.client.usecase.command.option.CommandOptionParser

class StateCommandOptionParser(
    stateCommandDefine: CommandRoute<FirstSubCommandName, StateCommandOptionName>,
): CommandOptionParser<StateCommandOptionName> {
    override val commandOptionDefines: List<CommandOptionDefine<StateCommandOptionName>> =
        stateCommandDefine.commandOptionDefines
    override fun parse(commandOptionDefine: CommandOptionDefine<StateCommandOptionName>, args: List<String>): CommandOption {
        throw UnsupportedOperationException()
    }
}