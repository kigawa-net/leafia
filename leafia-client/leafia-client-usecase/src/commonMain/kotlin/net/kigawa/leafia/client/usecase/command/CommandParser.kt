package net.kigawa.leafia.client.usecase.command

import net.kigawa.kodel.api.err.Res
import net.kigawa.leafia.client.domain.command.CommandErr
import net.kigawa.leafia.client.domain.command.arg.RawCommandParseRes

interface CommandParser {
    fun parse(commandArgs: List<String>): Res<RawCommandParseRes, CommandErr>
}