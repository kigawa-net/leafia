package net.kigawa.leafia.client.usecase.state.send

import net.kigawa.kodel.api.err.Res
import net.kigawa.leafia.client.domain.command.CommandErr
import net.kigawa.leafia.client.domain.command.CommandExecutor

class SendCommandExecutor(
    val server: String,
    val path: String,
): CommandExecutor {
    override fun execute(): Res<Unit, CommandErr> {
        TODO("Not yet implemented")
    }
}