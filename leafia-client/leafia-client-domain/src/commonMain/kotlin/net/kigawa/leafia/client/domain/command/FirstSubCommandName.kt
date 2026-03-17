package net.kigawa.leafia.client.domain.command

enum class FirstSubCommandName(override val value: String): CommandName {
    PUSH("push");

    override fun isMatch(arg: String): Boolean {
        return value == arg
    }
}