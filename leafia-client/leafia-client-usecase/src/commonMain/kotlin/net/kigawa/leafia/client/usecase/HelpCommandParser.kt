package net.kigawa.leafia.client.usecase

import net.kigawa.leafia.client.usecase.command.CommandParser

class HelpCommandParser(private val message: String): CommandParser {
    override fun parse(commandArgs: List<String>) {
        println("help")
        println(message)
        println("> leafia push")
    }
}