package net.kigawa.leafia.client.usecase.push

import net.kigawa.kodel.api.err.Res
import net.kigawa.leafia.client.domain.FirstSubCommandName
import net.kigawa.leafia.client.domain.PushCommandOptionName
import net.kigawa.leafia.client.domain.command.CommandErr
import net.kigawa.leafia.client.domain.command.CommandExecutor
import net.kigawa.leafia.client.domain.command.route.CommandRoute
import net.kigawa.leafia.client.usecase.AccessTokenLoader
import net.kigawa.leafia.client.usecase.command.CommandParser
import net.kigawa.leafia.client.usecase.command.option.CommandOptionParser

class PushCommandParser(
    pushCommandDefine: CommandRoute<FirstSubCommandName, PushCommandOptionName>,
): CommandParser<PushCommandOptionName> {
    val accessTokenLoader = AccessTokenLoader()
    override val optionParser: CommandOptionParser<PushCommandOptionName> = PushCommandOptionParser(pushCommandDefine)
    override fun parseCommand(
        parsers: List<CommandOptionParser<*>>,
        values: List<String>,
    ): Res<CommandExecutor, CommandErr> {
        TODO("Not yet implemented")
    }

}