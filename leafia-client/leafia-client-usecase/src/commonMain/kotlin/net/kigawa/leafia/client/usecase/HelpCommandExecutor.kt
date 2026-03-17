package net.kigawa.leafia.client.usecase

class HelpCommandExecutor(private val message: String): CommandExecutor {
    override fun execute() {
        println("help")
        println(message)
        println("> leafia push")
    }
}