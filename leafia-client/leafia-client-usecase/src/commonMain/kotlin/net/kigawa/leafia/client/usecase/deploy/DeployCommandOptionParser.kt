package net.kigawa.leafia.client.usecase.deploy

import net.kigawa.leafia.client.domain.DeployCommandOptionName
import net.kigawa.leafia.client.domain.FirstSubCommandName
import net.kigawa.leafia.client.domain.command.option.CommandOption
import net.kigawa.leafia.client.domain.command.option.CommandOptionDefine
import net.kigawa.leafia.client.domain.command.route.CommandRoute
import net.kigawa.leafia.client.usecase.command.option.CommandOptionParser

class DeployCommandOptionParser(
    deployCommandDefine: CommandRoute<FirstSubCommandName, DeployCommandOptionName>,
): CommandOptionParser<DeployCommandOptionName> {
    override val commandOptionDefines: List<CommandOptionDefine<DeployCommandOptionName>> =
        deployCommandDefine.commandOptionDefines
    override fun parse(commandOptionDefine: CommandOptionDefine<DeployCommandOptionName>, args: List<String>): CommandOption {
        throw UnsupportedOperationException()
    }
}