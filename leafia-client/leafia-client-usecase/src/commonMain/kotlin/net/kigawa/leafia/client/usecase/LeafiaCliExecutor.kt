package net.kigawa.leafia.client.usecase

import net.kigawa.leafia.client.domain.LeafiaCli
import net.kigawa.leafia.client.domain.command.arg.RawCommand
import net.kigawa.leafia.client.domain.leafia.LeafiaCommandRoutes
import net.kigawa.leafia.client.usecase.command.RawCommandArgsParser
import net.kigawa.leafia.client.usecase.command.leafia.LeafiaCommandParser
import net.kigawa.leafia.client.usecase.command.option.RawCommandOptionParser

class LeafiaCliExecutor(
    leafiaCli: LeafiaCli,
) {
    private val rawCommandOptionParser = RawCommandOptionParser()
    private val commandArgsParser = RawCommandArgsParser(rawCommandOptionParser)
    private val leafiaCommandRoutes: LeafiaCommandRoutes = leafiaCli.leafiaCommandDefine
    private val leafiaCommandParser = LeafiaCommandParser(
        commandArgsParser, leafiaCommandRoutes
    )

    fun execute(rawCommand: RawCommand) {
        commandArgsParser.parse(
            leafiaCommandRoutes, rawCommand.args, emptyList(),
            leafiaCommandParser,
        )
    }
}