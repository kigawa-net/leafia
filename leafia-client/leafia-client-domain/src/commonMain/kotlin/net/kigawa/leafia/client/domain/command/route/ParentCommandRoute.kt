package net.kigawa.leafia.client.domain.command.route

import net.kigawa.kodel.domain.routing.CircularRouteGroup
import net.kigawa.leafia.client.domain.command.define.CommandName
import net.kigawa.leafia.client.domain.command.option.CommandOptionName

abstract class ParentCommandRoute<C: CommandName, D: CommandName, O: CommandOptionName>:
    CircularRouteGroup<CommandRoute<D, *>, ParentCommandRoute<D, *, *>>(), CommandRoute<C, O>