package net.kigawa.leafia.client.usecase.push

import net.kigawa.leafia.client.domain.command.CommandExecutor

class PushCommandExecutor(
    val path: String,
    val followLog: Boolean,
): CommandExecutor