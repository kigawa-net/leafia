package net.kigawa.leafia.client.domain.command.option

interface CommandOptionDefine<O: CommandOptionName> {
    val name: O
    val valueSize: Int
}