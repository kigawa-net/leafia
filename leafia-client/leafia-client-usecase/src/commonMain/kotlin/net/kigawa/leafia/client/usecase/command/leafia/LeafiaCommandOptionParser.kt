package net.kigawa.leafia.client.usecase.command.leafia

import net.kigawa.leafia.client.usecase.command.option.RawCommandOptionParser
import net.kigawa.leafia.client.usecase.command.option.CommandOptionParser

class LeafiaCommandOptionParser(
    private val rawCommandOptionParser: RawCommandOptionParser
): CommandOptionParser {
    override fun parse(
        args: List<String>,
        parentOptionParsers: List<CommandOptionParser>,
    ): RawCommandOptionParser.Result {
        TODO("Not yet implemented")
    }
}