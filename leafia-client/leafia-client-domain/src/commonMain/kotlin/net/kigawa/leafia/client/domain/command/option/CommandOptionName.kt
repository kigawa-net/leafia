package net.kigawa.leafia.client.domain.command.option

interface CommandOptionName {
    fun isMatch(option: String): Boolean
}