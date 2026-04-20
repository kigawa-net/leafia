package net.kigawa.leafia.client.domain.state

import net.kigawa.leafia.client.domain.FirstSubCommandName
import net.kigawa.leafia.client.domain.ReceiveCommandOptionName
import net.kigawa.leafia.client.domain.SendCommandOptionName
import net.kigawa.leafia.client.domain.StateCommandOptionName
import net.kigawa.leafia.client.domain.StateSubCommandName
import net.kigawa.leafia.client.domain.command.option.CommandOptionDefine
import net.kigawa.leafia.client.domain.command.route.NonParentCommandRoute
import net.kigawa.leafia.client.domain.command.route.ParentCommandRoute

class StateCommandRoutes: ParentCommandRoute<
    FirstSubCommandName, StateSubCommandName, StateCommandOptionName
>() {
    override val name: FirstSubCommandName = FirstSubCommandName.STATE
    override val valueSize: Int = 0
    override val commandOptionDefines: List<CommandOptionDefine<StateCommandOptionName>> = emptyList()

    val receive = route {
        NonParentCommandRoute(
            StateSubCommandName.RECEIVE, 1,
            listOf<CommandOptionDefine<ReceiveCommandOptionName>>()
        )
    }
    val send = route {
        NonParentCommandRoute(
            StateSubCommandName.SEND, 1,
            listOf<CommandOptionDefine<SendCommandOptionName>>()
        )
    }
}