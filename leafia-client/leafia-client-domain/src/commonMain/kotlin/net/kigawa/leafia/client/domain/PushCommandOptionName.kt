package net.kigawa.leafia.client.domain

import net.kigawa.leafia.client.domain.command.option.CommandOptionDefine
import net.kigawa.leafia.client.domain.command.option.CommandOptionName

enum class PushCommandOptionName(private val flag: String): CommandOptionName {
    F("-f");

    override fun isMatch(option: String) = flag == option

    companion object {
        val F_DEFINE: CommandOptionDefine<PushCommandOptionName> = object: CommandOptionDefine<PushCommandOptionName> {
            override val name = F
            override val valueSize = 0
        }
    }
}