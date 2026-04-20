package net.kigawa.leafia.client.domain

import net.kigawa.leafia.client.domain.command.define.CommandName

enum class StateSubCommandName(override val value: String): CommandName {
    RECEIVE("receive"),
    SEND("send");

    override fun isMatch(arg: String) = value == arg
}