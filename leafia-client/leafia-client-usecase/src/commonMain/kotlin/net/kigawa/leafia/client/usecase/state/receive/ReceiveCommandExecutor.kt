package net.kigawa.leafia.client.usecase.state.receive

import net.kigawa.leafia.client.domain.command.CommandExecutor

class ReceiveCommandExecutor(
    val server: String,
    val path: String,
): CommandExecutor