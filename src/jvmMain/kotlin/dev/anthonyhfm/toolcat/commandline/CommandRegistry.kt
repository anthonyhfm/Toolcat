package dev.anthonyhfm.toolcat.commandline

import dev.anthonyhfm.toolcat.commandline.commands.VersionCommand
import kotlin.system.exitProcess

object CommandRegistry {
    private val commandList: Map<String, Command> = mapOf(
        "--version" to VersionCommand(),
        "-v" to VersionCommand(),
    )

    fun executeCommand(args: List<String>) {
        if (args.isNotEmpty()) {
            for (command in commandList) {
                if (command.key == args[0].lowercase()) {
                    command.value.executeCommand(args)
                    exitProcess(0)
                }
            }
        }
    }
}
