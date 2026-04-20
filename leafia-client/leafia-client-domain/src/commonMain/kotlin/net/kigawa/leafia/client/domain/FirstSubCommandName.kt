package net.kigawa.leafia.client.domain

import net.kigawa.leafia.client.domain.command.define.CommandName

enum class FirstSubCommandName(override val value: String): CommandName {
    PUSH("push"),
    DEPLOY("deploy"),
    STATE("state");

    override fun isMatch(arg: String): Boolean {
        return value == arg
    }
}