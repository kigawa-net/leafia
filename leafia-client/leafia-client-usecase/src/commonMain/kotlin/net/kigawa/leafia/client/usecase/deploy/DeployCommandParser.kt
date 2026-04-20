package net.kigawa.leafia.client.usecase.deploy

import net.kigawa.kodel.api.err.Res
import net.kigawa.kodel.api.err.ok
import net.kigawa.leafia.client.domain.DeployCommandOptionName
import net.kigawa.leafia.client.domain.FirstSubCommandName
import net.kigawa.leafia.client.domain.command.CommandErr
import net.kigawa.leafia.client.domain.command.CommandExecutor
import net.kigawa.leafia.client.domain.command.option.CommandOption
import net.kigawa.leafia.client.domain.command.route.CommandRoute
import net.kigawa.leafia.client.usecase.command.CommandParser
import net.kigawa.leafia.client.usecase.command.option.CommandOptionParser

class DeployCommandParser(
    deployCommandDefine: CommandRoute<FirstSubCommandName, DeployCommandOptionName>,
): CommandParser<DeployCommandOptionName> {
    override val optionParser: CommandOptionParser<DeployCommandOptionName> =
        DeployCommandOptionParser(deployCommandDefine)

    override fun parseCommand(
        parsers: List<CommandOptionParser<*>>,
        values: List<String>,
        options: List<CommandOption>,
    ): Res<CommandExecutor, CommandErr> {
        val path = values.getOrNull(0) ?: "leafia.yml"
        return DeployCommandExecutor(path).ok()
    }
}