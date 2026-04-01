package net.kigawa.leafia.client.usecase.command.option

interface CommandOptionParser {
    fun parse(args: List<String>, parentOptionParsers: List<CommandOptionParser>): RawCommandOptionParser.Result
}