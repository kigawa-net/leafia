package net.kigawa.leafia.client.usecase.command.leafia

import net.kigawa.leafia.client.domain.command.option.CommandOption
import net.kigawa.leafia.client.domain.command.option.CommandOptionDefine
import net.kigawa.leafia.client.domain.leafia.LeafiaCommandOptionName
import net.kigawa.leafia.client.usecase.command.option.CommandOptionParser

class LeafiaCommandOptionParser: CommandOptionParser<LeafiaCommandOptionName> {
    override val commandOptionDefines: List<CommandOptionDefine<LeafiaCommandOptionName>> = emptyList()
    override fun parse(commandOptionDefine: CommandOptionDefine<LeafiaCommandOptionName>, args: List<String>): CommandOption {
        throw UnsupportedOperationException()
    }
}