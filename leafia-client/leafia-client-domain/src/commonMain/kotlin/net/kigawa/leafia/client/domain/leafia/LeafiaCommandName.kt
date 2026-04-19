package net.kigawa.leafia.client.domain.leafia

import net.kigawa.leafia.client.domain.command.define.CommandName

object LeafiaCommandName: CommandName {
    override fun isMatch(arg: String): Boolean {
        return value == arg
    }

    override val value: String
        get() = "leafia"
}