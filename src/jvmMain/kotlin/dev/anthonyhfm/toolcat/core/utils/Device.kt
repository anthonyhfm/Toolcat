package dev.anthonyhfm.toolcat.core.utils

import java.io.File
import java.util.*

enum class OperatingSystem {
    WINDOWS,
    LINUX,
    MACOS,
    UNKNOWN
}

object Device {
    private var operatingSystem: OperatingSystem? = null

    fun getOS(): OperatingSystem {
        if (operatingSystem != null) {
            return operatingSystem!!
        }

        val os = System.getProperty("os.name").lowercase(Locale.getDefault())

        if (os.contains("windows")) {
            operatingSystem = OperatingSystem.WINDOWS
        }

        if (os.contains("mac os x")) {
            operatingSystem = OperatingSystem.MACOS
        }

        if (os.contains("linux")) {
            operatingSystem = OperatingSystem.LINUX
        }

        if(operatingSystem == null) {
            operatingSystem = OperatingSystem.UNKNOWN
        }

        return operatingSystem!!
    }

    fun createUserDir() {
        val home = System.getProperty("user.home")

        File("$home/Toolcat").mkdir()
        // File("$home/Toolcat/Config").mkdir()
        // val copyThemes = File("$home/Toolcat/Themes").mkdir()
        File("$home/Toolcat/Extensions").mkdir()
    }
}
