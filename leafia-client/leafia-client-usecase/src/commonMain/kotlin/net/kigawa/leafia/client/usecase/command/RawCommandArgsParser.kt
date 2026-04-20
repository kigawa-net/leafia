package net.kigawa.leafia.client.usecase.command

import net.kigawa.kodel.api.err.Res
import net.kigawa.kodel.api.err.err
import net.kigawa.kodel.api.err.ok
import net.kigawa.leafia.client.domain.command.CommandErr
import net.kigawa.leafia.client.domain.command.CommandExecutor
import net.kigawa.leafia.client.domain.command.define.CommandName
import net.kigawa.leafia.client.domain.command.option.CommandOption
import net.kigawa.leafia.client.domain.command.option.CommandOptionName
import net.kigawa.leafia.client.domain.command.route.CommandRoute
import net.kigawa.leafia.client.domain.command.route.ParentCommandRoute
import net.kigawa.leafia.client.domain.unwrap
import net.kigawa.leafia.client.usecase.command.option.CommandOptionParser
import net.kigawa.leafia.client.usecase.command.option.RawCommandOptionParser

class RawCommandArgsParser(
    private val rawCommandOptionParser: RawCommandOptionParser,
) {
    private data class ParsedArgs(
        val args: List<String>,
        val options: List<CommandOption>,
        val values: List<String>,
    )

    private fun parseArgsAndValues(
        commandArgs: List<String>,
        valueSize: Int,
        optionParsers: List<CommandOptionParser<*>>,
    ): Res<ParsedArgs, CommandErr> {
        var args = commandArgs
        val options = rawCommandOptionParser.parse(args, optionParsers).unwrap {
            return it.err()
        }.let {
            args = it.args
            it.options
        }.toMutableList()
        val values = mutableListOf<String>()
        repeat(valueSize) {
            val value = args.firstOrNull() ?: return CommandErr("require value").err()
            args = args.drop(1)

            options += rawCommandOptionParser.parse(args, optionParsers).unwrap {
                return it.err()
            }.let {
                args = it.args
                it.options
            }
            values.add(value)
        }
        return ParsedArgs(args, options, values).ok()
    }

    fun <C: CommandName, O: CommandOptionName, D: CommandName, P: CommandOptionName> parse(
        commandRoutes: ParentCommandRoute<C, D, O>, commandArgs: List<String>,
        parentOptionParsers: List<CommandOptionParser<*>>,
        commandParser: ParentCommandParser<C, O, D, P>,
    ): Res<CommandExecutor, CommandErr> {
        val parsed = when (val r = parseArgsAndValues(
            commandArgs, commandRoutes.valueSize,
            parentOptionParsers + commandParser.optionParser
        )) {
            is Res.Ok -> r.value
            is Res.Err -> return r.err.err()
        }
        var args = parsed.args
        val values = parsed.values
        val options = parsed.options
        val subCommandName = args.firstOrNull() ?: return commandParser.parseSubCommand(
            null, args, parentOptionParsers, values, options
        )
        args = args.drop(1)
        val commandDefine = commandRoutes.routes
            .first { it.name.isMatch(subCommandName) }

        return commandParser
            .parseSubCommand(
                commandDefine, args, parentOptionParsers + commandParser.optionParser, values, options
            )
    }

    fun <C: CommandName, O: CommandOptionName> parse(
        commandRoute: CommandRoute<C, O>, commandArgs: List<String>,
        parentOptionParsers: List<CommandOptionParser<*>>,
        commandParser: CommandParser<O>,
    ): Res<CommandExecutor, CommandErr> {
        val parsed = when (val r = parseArgsAndValues(
            commandArgs, commandRoute.valueSize,
            parentOptionParsers + commandParser.optionParser
        )) {
            is Res.Ok -> r.value
            is Res.Err -> return r.err.err()
        }
        return commandParser.parseCommand(parentOptionParsers, parsed.values + parsed.args, parsed.options)
    }
}