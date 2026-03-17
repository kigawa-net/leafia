package net.kigawa.leafia.client.domain.command

class NonParentCommandRoute<T: CommandName>(
    override val name: T,
    override val valueSize: Int,
    override val commandOptionDefines: List<CommandOption>,
): CommandRoute<T>