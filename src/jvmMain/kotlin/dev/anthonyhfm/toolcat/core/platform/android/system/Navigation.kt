package dev.anthonyhfm.toolcat.core.platform.android.system

import dev.anthonyhfm.toolcat.core.platform.android.AndroidDevice
fun AndroidDevice.navigateHome() {
    adb.shell("input keyevent 3")
}

fun AndroidDevice.navigateBack() {
    adb.shell("input keyevent 4")
}
