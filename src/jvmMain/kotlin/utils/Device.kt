package utils

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
}