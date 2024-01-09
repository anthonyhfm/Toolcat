package dev.anthonyhfm.toolcat.commandline

interface Command {
    fun executeCommand(args: List<String>)
}
