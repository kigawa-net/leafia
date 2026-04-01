package net.kigawa.leafia.client

import net.kigawa.leafia.client.domain.command.arg.RawCommand
import net.kigawa.leafia.client.domain.LeafiaCli
import net.kigawa.leafia.client.usecase.LeafiaCliExecutor

class App(private val args: Array<String>) {
    private val leafiaCliExecutor = LeafiaCliExecutor()
    fun start() {
        val leafiaCli = LeafiaCli()
        val rawCommand = RawCommand(args.toList())
        leafiaCliExecutor.execute(rawCommand)
    }
}