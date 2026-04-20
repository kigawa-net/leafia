package net.kigawa.leafia.client.domain.command

import net.kigawa.kodel.api.err.Res

interface CommandExecutor {

    fun execute(): Res<Unit, CommandErr>
}