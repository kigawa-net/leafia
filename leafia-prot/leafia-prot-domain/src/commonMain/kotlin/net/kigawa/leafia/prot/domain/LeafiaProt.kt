package net.kigawa.leafia.prot.domain

import net.kigawa.kodel.domain.routing.CircularRouteGroup
import net.kigawa.kodel.domain.routing.RouteGroup

class LeafiaProt: CircularRouteGroup<Unit, RouteGroup<*>>() {
}