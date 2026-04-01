package net.kigawa.leafia.client.domain.command.define

import net.kigawa.leafia.client.domain.command.option.CommandOptionDefine
import net.kigawa.leafia.client.domain.command.option.CommandOptionName

data class CommandDefine<O: CommandOptionName>(
    val name: String,
    val valueSize: Int,
    val commandOptions: List<CommandOptionDefine<O>>,
    val subCommandDefines: List<CommandDefine<*>>,
)