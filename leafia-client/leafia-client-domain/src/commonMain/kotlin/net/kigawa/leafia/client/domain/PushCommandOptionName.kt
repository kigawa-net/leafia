package net.kigawa.leafia.client.domain

import net.kigawa.leafia.client.domain.command.option.CommandOptionName

class PushCommandOptionName: CommandOptionName {
    override fun isMatch(option: String): Boolean {
        return false
    }
}