package net.kigawa.leafia.client.domain.command

interface CommandOptionName {
    fun isMatch(option: String): Boolean
}