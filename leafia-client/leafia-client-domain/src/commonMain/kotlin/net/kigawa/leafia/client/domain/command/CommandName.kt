package net.kigawa.leafia.client.domain.command

interface CommandName {
    fun isMatch(arg: String): Boolean

    val value: String
}