package dev.anthonyhfm.toolcat.core.platform.android.system

import dev.anthonyhfm.toolcat.core.platform.android.AndroidDevice
import java.io.InputStream

fun AndroidDevice.getScreenshot(): InputStream {
    var buffer: ByteArray = arrayOf<Byte>().toByteArray()

    adb.openShell("screencap -p").use {
        var read = true

        while (read) {
            try {
                buffer = buffer.plus(it.read().payload)
            } catch (ex: Exception) { // Reached end of buffer
                read = false
            }
        }
    }

    return buffer.inputStream()
}