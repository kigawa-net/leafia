package net.kigawa.leafia.client.domain.command.route

import net.kigawa.leafia.client.domain.command.define.CommandName
import net.kigawa.leafia.client.domain.command.option.CommandOptionDefine
import net.kigawa.leafia.client.domain.command.option.CommandOptionName

interface CommandRoute<C: CommandName, O: CommandOptionName> {
    val name: C
    val valueSize: Int
    val commandOptionDefines: List<CommandOptionDefine<O>>
}