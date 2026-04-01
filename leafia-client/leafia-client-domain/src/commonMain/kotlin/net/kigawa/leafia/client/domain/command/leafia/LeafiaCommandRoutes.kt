package net.kigawa.leafia.client.domain.command.leafia

import net.kigawa.leafia.client.domain.FirstSubCommandName
import net.kigawa.leafia.client.domain.command.option.CommandOptionDefine
import net.kigawa.leafia.client.domain.command.option.CommandOptionName
import net.kigawa.leafia.client.domain.command.route.NonParentCommandRoute
import net.kigawa.leafia.client.domain.command.route.ParentCommandRoute

class LeafiaCommandRoutes: ParentCommandRoute<
    LeafiaCommandName, FirstSubCommandName, LeafiaCommandOptionName, CommandOptionName
    >() {
    override val name: LeafiaCommandName
        get() = LeafiaCommandName
    override val valueSize: Int
        get() = 0
    override val commandOptionDefines: List<CommandOptionDefine<LeafiaCommandOptionName>>
        get() = emptyList()
    val push = route {
        NonParentCommandRoute(FirstSubCommandName.PUSH, 1, listOf())
    }
}