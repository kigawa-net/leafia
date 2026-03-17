package net.kigawa.leafia.client.domain.command

data class CommandDefine (
    val name: String,
    val valueSize: Int,
    val commandOptions: List<CommandOption>,
    val subCommandDefines: List<CommandDefine>,
)