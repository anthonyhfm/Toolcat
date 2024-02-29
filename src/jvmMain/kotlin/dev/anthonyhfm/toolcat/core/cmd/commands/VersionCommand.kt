package dev.anthonyhfm.toolcat.core.cmd.commands

import dev.anthonyhfm.toolcat.core.cmd.Command

class VersionCommand : Command {
    override fun executeCommand(args: List<String>) {
        println("Toolcat by Anthony Hofmeister - Version ${System.getProperty("app.version")}")
    }
}
