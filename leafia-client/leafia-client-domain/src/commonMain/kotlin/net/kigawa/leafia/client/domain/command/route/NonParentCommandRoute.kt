package net.kigawa.leafia.client.domain.command.route

import net.kigawa.leafia.client.domain.command.define.CommandName
import net.kigawa.leafia.client.domain.command.option.CommandOptionDefine
import net.kigawa.leafia.client.domain.command.option.CommandOptionName

class NonParentCommandRoute<C: CommandName, O: CommandOptionName>(
    override val name: C,
    override val valueSize: Int,
    override val commandOptionDefines: List<CommandOptionDefine<O>>,
): CommandRoute<C,O>