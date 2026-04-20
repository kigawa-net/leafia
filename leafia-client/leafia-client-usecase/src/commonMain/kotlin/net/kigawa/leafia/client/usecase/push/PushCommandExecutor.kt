package net.kigawa.leafia.client.usecase.push

import net.kigawa.kodel.api.err.Res
import net.kigawa.leafia.client.domain.command.CommandErr
import net.kigawa.leafia.client.domain.command.CommandExecutor

class PushCommandExecutor(
    val path: String,
    val followLog: Boolean,
): CommandExecutor {
    override fun execute(): Res<Unit, CommandErr> {
        TODO("Not yet implemented")
    }
}