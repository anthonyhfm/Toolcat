package dev.anthonyhfm.toolcat.core.platform.android.system

import dev.anthonyhfm.toolcat.core.platform.android.AndroidDevice
import dev.anthonyhfm.toolcat.core.utils.Shell
import kotlinx.coroutines.runBlocking
import mobile.firmware.getScreenshot
import okio.sink
import java.io.File
import java.io.InputStream

fun AndroidDevice.getScreenshot(): InputStream? {
    return Shell.getInputStream("adb -s ${this.serial} shell screencap -p")
}
