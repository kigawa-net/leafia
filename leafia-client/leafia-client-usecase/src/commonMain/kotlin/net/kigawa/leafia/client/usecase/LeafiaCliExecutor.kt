package net.kigawa.leafia.client.usecase

import net.kigawa.leafia.client.domain.command.RawCommandArgs
import net.kigawa.leafia.client.domain.LeafiaCli

class LeafiaCliExecutor {
    private val commandArgsParser = CommandArgsParser()
    fun execute(leafiaCli: LeafiaCli, rawCommandArgs: RawCommandArgs) {
        val commandExecutor = commandArgsParser.parse(leafiaCli,rawCommandArgs)
        commandExecutor.execute()
    }
}