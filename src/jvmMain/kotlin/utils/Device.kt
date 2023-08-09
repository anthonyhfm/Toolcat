package utils

import java.util.*

enum class OperatingSystem {
    WINDOWS,
    LINUX,
    MACOS,
    UNKNOWN
}

object Device {
    fun getOS(): OperatingSystem {
        val os = System.getProperty("os.name").lowercase(Locale.getDefault())

        if (os.contains("windows")) {
            return OperatingSystem.WINDOWS
        }

        if (os.contains("mac os x")) {
            return OperatingSystem.MACOS
        }

        if (os.contains("linux")) {
            return OperatingSystem.LINUX
        }

        return OperatingSystem.UNKNOWN
    }
}