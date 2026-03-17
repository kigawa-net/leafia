package net.kigawa.leafia.client.domain.command

interface CommandRoute<T: CommandName> {
    val name: T
    val valueSize: Int
    val commandOptionDefines: List<CommandOption>
}