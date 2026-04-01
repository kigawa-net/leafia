package net.kigawa.leafia.client.domain.command.define

interface CommandName {
    fun isMatch(arg: String): Boolean

    val value: String
}