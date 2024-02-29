package dev.anthonyhfm.toolcat.core.cmd

import dev.anthonyhfm.toolcat.core.cmd.commands.DoctorCommand
import dev.anthonyhfm.toolcat.core.cmd.commands.VersionCommand
import kotlin.system.exitProcess

object CommandRegistry {
    private val commandList: Map<String, Command> = mapOf(
        "--version" to VersionCommand(),
        "-v" to VersionCommand(),
        "doctor" to DoctorCommand()
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
