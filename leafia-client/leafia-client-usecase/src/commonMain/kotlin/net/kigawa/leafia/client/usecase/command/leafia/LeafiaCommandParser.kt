package net.kigawa.leafia.client.usecase.command.leafia

import net.kigawa.kodel.api.err.Res
import net.kigawa.leafia.client.domain.FirstSubCommandName
import net.kigawa.leafia.client.domain.command.CommandErr
import net.kigawa.leafia.client.domain.command.arg.RawCommandParseRes
import net.kigawa.leafia.client.domain.command.leafia.LeafiaCommandRoutes
import net.kigawa.leafia.client.usecase.PushCommandParser
import net.kigawa.leafia.client.usecase.command.CommandArgsParser
import net.kigawa.leafia.client.usecase.command.CommandParser
import net.kigawa.leafia.client.usecase.command.option.RawCommandOptionParser

class LeafiaCommandParser(
    private val leafiaCommandRoutes: LeafiaCommandRoutes,
): CommandParser {
    private val commandArgsParser = CommandArgsParser()
    private val rawCommandOptionParser = RawCommandOptionParser()
    override fun parse(commandArgs: List<String>): Res<RawCommandParseRes, CommandErr> =
        commandArgsParser.parse(leafiaCommandRoutes, commandArgs, emptyList()) {
            when (name) {
                FirstSubCommandName.PUSH -> PushCommandParser()
            }
        }

}