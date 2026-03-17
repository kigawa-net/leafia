package net.kigawa.leafia.client.domain.command

class LeafiaCommandRoutes: ParentCommandRoute<LeafiaCommandName, FirstSubCommandName>() {
    override val name: LeafiaCommandName
        get() = LeafiaCommandName
    override val valueSize: Int
        get() = 0
    override val commandOptionDefines: List<CommandOption>
        get() = emptyList()
    val push = route {
        NonParentCommandRoute(FirstSubCommandName.PUSH, 1, listOf())
    }
}