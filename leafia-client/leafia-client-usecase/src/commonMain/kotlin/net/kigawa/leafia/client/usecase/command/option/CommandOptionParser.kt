package net.kigawa.leafia.client.usecase.command.option

import net.kigawa.leafia.client.domain.command.option.CommandOption
import net.kigawa.leafia.client.domain.command.option.CommandOptionDefine
import net.kigawa.leafia.client.domain.command.option.CommandOptionName

interface CommandOptionParser<O: CommandOptionName> {
    val commandOptionDefines: List<CommandOptionDefine<O>>
    fun parse(commandOptionDefine: CommandOptionDefine<O>, args: List<String>): CommandOption
}