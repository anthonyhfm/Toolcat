package dev.anthonyhfm.toolcat.core.platform.android.system

import dev.anthonyhfm.toolcat.core.platform.android.AndroidDevice
import dev.anthonyhfm.toolcat.core.utils.Shell
import mobile.firmware.navigateBack
import mobile.firmware.navigateToHome

fun AndroidDevice.navigateHome() {
    adb.shell("input keyevent 4")
}

fun AndroidDevice.navigateBack() {
    adb.shell("input keyevent 3")
}
