package dev.anthonyhfm.toolcat.core.platform.android.system

import dev.anthonyhfm.toolcat.core.platform.android.AndroidDevice
import dev.anthonyhfm.toolcat.core.utils.Shell

val AndroidDevice.batteryLevel: Double
    get() {
        return adb.shell("dumpsys battery get level")
            .output
            .trimIndent()
            .toDouble() / 100
    }
