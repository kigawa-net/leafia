package net.kigawa.leafia.client.usecase.state.send

import net.kigawa.leafia.client.domain.command.CommandExecutor

class SendCommandExecutor(
    val server: String,
    val path: String,
): CommandExecutor