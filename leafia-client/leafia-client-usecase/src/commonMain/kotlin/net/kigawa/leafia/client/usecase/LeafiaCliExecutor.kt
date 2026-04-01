package net.kigawa.leafia.client.usecase

import net.kigawa.leafia.client.domain.LeafiaCli
import net.kigawa.leafia.client.domain.command.arg.RawCommand
import net.kigawa.leafia.client.usecase.command.leafia.LeafiaCommandParser

class LeafiaCliExecutor(
    leafiaCli: LeafiaCli,
) {
    private val leafiaCommandParser = LeafiaCommandParser(leafiaCli.leafiaCommandDefine)
    fun execute(rawCommand: RawCommand) {
        leafiaCommandParser.parse(rawCommand.args)
    }
}