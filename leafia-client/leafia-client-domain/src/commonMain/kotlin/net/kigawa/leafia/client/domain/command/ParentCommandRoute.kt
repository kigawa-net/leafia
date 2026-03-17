package net.kigawa.leafia.client.domain.command

import net.kigawa.kodel.domain.routing.CircularRouteGroup

abstract class ParentCommandRoute<T: CommandName, U: CommandName>:
    CircularRouteGroup<CommandRoute<U>, ParentCommandRoute<U, *>>(), CommandRoute<T>