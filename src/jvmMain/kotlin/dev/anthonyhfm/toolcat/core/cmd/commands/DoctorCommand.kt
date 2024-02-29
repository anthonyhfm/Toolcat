package dev.anthonyhfm.toolcat.core.cmd.commands

import dev.anthonyhfm.toolcat.core.cmd.Command
import dev.anthonyhfm.toolcat.core.doctor.Doctor

class DoctorCommand : Command {
    override fun executeCommand(args: List<String>) {
        println("Toolcat Doctor\n")

        Doctor.checkDependencies().forEach {
            if (it.available) {
                println("[ FOUND ] - ${it.dependency.name}")
                println("    - Purpose: ${it.dependency.purpose}")
            } else {
                println("[ NOT FOUND!! ] - ${it.dependency.name}")
                println("    - Tested: ${it.dependency.command}")
                println("    - Available: ${it.available}")
                println("    - Purpose: ${it.dependency.purpose}")
                println("Please install this program by following this link if you want to use that Feature: ${it.dependency.instructions}")
            }
        }
    }
}