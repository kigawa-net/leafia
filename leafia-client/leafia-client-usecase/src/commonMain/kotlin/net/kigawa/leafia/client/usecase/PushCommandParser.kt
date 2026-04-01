package net.kigawa.leafia.client.usecase

import net.kigawa.kodel.api.err.Res
import net.kigawa.leafia.client.domain.command.CommandErr
import net.kigawa.leafia.client.domain.command.CommandExecutor
import net.kigawa.leafia.client.usecase.command.CommandParser

class PushCommandParser: CommandParser {
    val accessTokenLoader = AccessTokenLoader()
    override fun parse(commandArgs: List<String>): Res<CommandExecutor, CommandErr> {
        accessTokenLoader.load()
        TODO("Not yet implemented")
    }
}