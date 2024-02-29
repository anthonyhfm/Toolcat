package dev.anthonyhfm.toolcat.core.cmd

interface Command {
    fun executeCommand(args: List<String>)
}
