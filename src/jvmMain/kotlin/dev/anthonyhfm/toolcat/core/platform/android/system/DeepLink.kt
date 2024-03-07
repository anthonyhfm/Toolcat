package dev.anthonyhfm.toolcat.core.platform.android.system

import dev.anthonyhfm.toolcat.core.platform.android.AndroidDevice
import dev.anthonyhfm.toolcat.core.utils.Shell
import mobile.firmware.openDeepLink

fun AndroidDevice.openDeepLink(deepLink: String) {
    adb.shell("am start -a android.intent.action.VIEW -d \"$deepLink\"")
}
