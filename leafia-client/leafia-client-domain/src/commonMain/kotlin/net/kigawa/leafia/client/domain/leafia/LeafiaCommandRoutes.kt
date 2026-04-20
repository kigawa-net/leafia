package net.kigawa.leafia.client.domain.leafia

import net.kigawa.leafia.client.domain.DeployCommandOptionName
import net.kigawa.leafia.client.domain.FirstSubCommandName
import net.kigawa.leafia.client.domain.PushCommandOptionName
import net.kigawa.leafia.client.domain.command.option.CommandOptionDefine
import net.kigawa.leafia.client.domain.command.route.NonParentCommandRoute
import net.kigawa.leafia.client.domain.command.route.ParentCommandRoute
import net.kigawa.leafia.client.domain.state.StateCommandRoutes

class LeafiaCommandRoutes: ParentCommandRoute<
    LeafiaCommandName, FirstSubCommandName, LeafiaCommandOptionName
    >() {
    override val name: LeafiaCommandName
        get() = LeafiaCommandName
    override val valueSize: Int
        get() = 0
    override val commandOptionDefines: List<CommandOptionDefine<LeafiaCommandOptionName>>
        get() = emptyList()

    val push = route {
        NonParentCommandRoute(
            FirstSubCommandName.PUSH, 0,
            listOf(PushCommandOptionName.F_DEFINE)
        )
    }

    val deploy = route {
        NonParentCommandRoute(
            FirstSubCommandName.DEPLOY, 0,
            listOf<CommandOptionDefine<DeployCommandOptionName>>()
        )
    }

    val state = route {
        StateCommandRoutes()
    }
}