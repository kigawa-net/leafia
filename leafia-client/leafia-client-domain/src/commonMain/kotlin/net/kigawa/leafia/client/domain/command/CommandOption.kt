package net.kigawa.leafia.client.domain.command

interface CommandOption {
    val name: CommandOptionName
    val valueSize: Int
}