package dev.anthonyhfm.toolcat.core.platform.android.system

import dev.anthonyhfm.toolcat.core.platform.android.AndroidDevice

fun AndroidDevice.openDeepLink(deepLink: String) {
    adb.shell("am start -a android.intent.action.VIEW -d \"$deepLink\"")
}
