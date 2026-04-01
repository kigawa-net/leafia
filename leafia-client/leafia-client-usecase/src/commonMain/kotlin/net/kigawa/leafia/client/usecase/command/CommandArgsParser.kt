package net.kigawa.leafia.client.usecase.command

import net.kigawa.kodel.api.err.Res
import net.kigawa.kodel.api.err.err
import net.kigawa.kodel.api.err.ok
import net.kigawa.leafia.client.domain.command.CommandErr
import net.kigawa.leafia.client.domain.command.define.CommandName
import net.kigawa.leafia.client.domain.command.arg.RawCommandParseRes
import net.kigawa.leafia.client.domain.command.option.CommandOptionName
import net.kigawa.leafia.client.domain.command.route.CommandRoute
import net.kigawa.leafia.client.domain.command.route.ParentCommandRoute
import net.kigawa.leafia.client.domain.unwrap
import net.kigawa.leafia.client.usecase.command.option.CommandOptionParser

class CommandArgsParser {


    fun <C: CommandName, O: CommandOptionName> parse(
        commandRoutes: ParentCommandRoute<C, C, O, O>, commandArgs: List<String>,
        parentOptionParsers: List<CommandOptionParser>, commandOptionParser: CommandOptionParser,
        routeCommand: CommandRoute<C, O>.() -> CommandParser,
    ): Res<RawCommandParseRes, CommandErr> {
        val arg = commandArgs.firstOrNull() ?: return CommandErr("require subcommand").err()
        var args = commandArgs.drop(1)
        val options = commandOptionParser.parse(args, parentOptionParsers).let {
            args = it.args
            it.options
        }.toMutableList()
        val commandDefine = commandRoutes.routes
            .first { it.name.isMatch(arg) }
        val executor = commandDefine.routeCommand().parse(args).unwrap {
            return it.err()
        }.let {
            args = it.args
            it.executor
        }
        options += commandOptionParser.parse(args, parentOptionParsers).let {
            args = it.args
            it.options
        }
        return RawCommandParseRes(executor, args, options).ok()
    }
}