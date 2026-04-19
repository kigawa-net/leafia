package net.kigawa.leafia.client.usecase.push

import net.kigawa.leafia.client.domain.FirstSubCommandName
import net.kigawa.leafia.client.domain.PushCommandOptionName
import net.kigawa.leafia.client.domain.command.option.CommandOption
import net.kigawa.leafia.client.domain.command.option.CommandOptionDefine
import net.kigawa.leafia.client.domain.command.route.CommandRoute
import net.kigawa.leafia.client.usecase.command.option.CommandOptionParser

class PushCommandOptionParser(
    pushCommandDefine: CommandRoute<FirstSubCommandName, PushCommandOptionName>,
): CommandOptionParser<PushCommandOptionName> {
    override val commandOptionDefines: List<CommandOptionDefine<PushCommandOptionName>> =
        pushCommandDefine.commandOptionDefines

    override fun parse(
        commandOptionDefine: CommandOptionDefine<PushCommandOptionName>,
        args: List<String>,
    ): CommandOption {
        TODO("Not yet implemented")
    }
}