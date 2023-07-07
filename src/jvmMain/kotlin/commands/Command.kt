package commands

interface Command {
    val commandName: String

    fun executeCommand(args: List<String>)
}