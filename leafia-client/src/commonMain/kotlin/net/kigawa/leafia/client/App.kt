package net.kigawa.leafia.client

import net.kigawa.leafia.client.domain.LeafiaCli
import net.kigawa.leafia.client.domain.command.arg.RawCommand
import net.kigawa.leafia.client.usecase.LeafiaCliExecutor

class App(private val args: Array<String>) {
    fun start() {
        val leafiaCli = LeafiaCli()
        val leafiaCliExecutor = LeafiaCliExecutor(leafiaCli)
        val rawCommand = RawCommand(args.toList())
        leafiaCliExecutor.execute(rawCommand)
    }
}