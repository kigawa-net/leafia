package net.kigawa.leafia.client.usecase

import net.kigawa.leafia.client.domain.LeafiaCli
import net.kigawa.leafia.client.domain.command.CommandOptionName
import net.kigawa.leafia.client.domain.command.FirstSubCommandName
import net.kigawa.leafia.client.domain.command.RawCommandArgs

class CommandArgsParser {
    fun parse(leafiaCli: LeafiaCli, rawCommandArgs: RawCommandArgs): CommandExecutor {
        val args = rawCommandArgs.args.toMutableList()
        val arg = args.removeFirstOrNull() ?: return HelpCommandExecutor("require subcommand")
        val commandDefine = leafiaCli.leafiaCommandDefine.routes
            .first { it.name.isMatch(arg) }
        val values = (0 until commandDefine.valueSize)
            .map { args.removeFirstOrNull() ?: return HelpCommandExecutor("require value") }
        val optionDefines = commandDefine.commandOptionDefines
        val options = mutableMapOf<CommandOptionName, List<String>>()
        while (args.firstOrNull()?.startsWith("-") ?: false) {
            val option = args.removeFirst()
            val optionDefine = optionDefines
                .firstOrNull { it.name.isMatch(option) } ?: return HelpCommandExecutor("unknown option $option")

            val optionValues = (0 until optionDefine.valueSize)
                .map { args.removeFirstOrNull() ?: return HelpCommandExecutor("require option value") }
            options[optionDefine.name] =
                options.getOrDefault(optionDefine.name, listOf()) + optionValues
        }
        return when (commandDefine.name) {
            FirstSubCommandName.PUSH -> PushCommandExecutor(values, options)
        }
    }
}