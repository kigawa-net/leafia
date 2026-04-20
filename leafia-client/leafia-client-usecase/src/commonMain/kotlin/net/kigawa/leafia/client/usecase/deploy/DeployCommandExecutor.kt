package net.kigawa.leafia.client.usecase.deploy

import net.kigawa.leafia.client.domain.command.CommandExecutor

class DeployCommandExecutor(
    val path: String,
): CommandExecutor