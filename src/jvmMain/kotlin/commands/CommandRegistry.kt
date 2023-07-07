package commands

import kotlin.system.exitProcess

class CommandRegistry {
    val commandList: List<Command> = listOf<Command>(

    )

    fun executeCommand(args: List<String>) {
        for (command in commandList) {
            if (command.commandName == args[0].lowercase()) {
                command.executeCommand(args)
            }
        }
    }
}