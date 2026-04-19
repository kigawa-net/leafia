package net.kigawa.leafia.client.usecase.command.option

import net.kigawa.kodel.api.err.Res
import net.kigawa.kodel.api.err.err
import net.kigawa.kodel.api.err.ok
import net.kigawa.leafia.client.domain.command.CommandErr
import net.kigawa.leafia.client.domain.command.option.CommandOption
import net.kigawa.leafia.client.domain.command.option.CommandOptionName
import net.kigawa.leafia.client.domain.unwrap

class RawCommandOptionParser {
    fun parse(
        args: List<String>, commandOptionParsers: List<CommandOptionParser<*>>,
    ): Res<Result, CommandErr> {
        val options = mutableListOf<CommandOption>()
        var mtArgs = args
        loop@ while (mtArgs.firstOrNull()?.startsWith("-") ?: false) {
            val option = mtArgs.first()
            commandOptionParsers.reversed().forEach { commandOptionParser ->
                parseSingle(commandOptionParser, option, mtArgs)?.unwrap {
                    return it.err()
                }?.let {
                    mtArgs = it.args
                    options += it.options
                } ?: return@forEach
                continue@loop
            }
            return CommandErr("unknown option").err()
        }
        return Result(options, mtArgs).ok()
    }

    private fun <O: CommandOptionName> parseSingle(
        commandOptionParser: CommandOptionParser<O>, option: String, args: List<String>,
    ): Res<Result, CommandErr>? {
        val optionDefine = commandOptionParser.commandOptionDefines
            .firstOrNull { it.name.isMatch(option) } ?: return null

        val optionValues = (0 until optionDefine.valueSize)
            .map { args.getOrNull(it) ?: return CommandErr("require option value").err() }
        val mtArgs = args.drop(optionDefine.valueSize)
        val options = commandOptionParser.parse(optionDefine, optionValues)
        return Result(listOf(options), mtArgs).ok()
    }

    data class Result(
        val options: List<CommandOption>,
        val args: List<String>,
    )
}