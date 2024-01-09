package dev.anthonyhfm.toolcat.commandline.commands

import dev.anthonyhfm.toolcat.commandline.Command

class VersionCommand : Command {
    override fun executeCommand(args: List<String>) {
        println("Toolcat by anthonyhfm - Version {pull from config}")
    }
}
