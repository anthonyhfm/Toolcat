package mobile.firmware

import mobile.DeviceType
import mobile.MobileDevice
import java.io.IOException
import java.io.InputStream
import java.lang.Exception

fun MobileDevice.getScreenshot(): InputStream? {
    return when (this.deviceType) {
        DeviceType.ANDROID -> {
            try {
                val process = Runtime.getRuntime().exec("adb -s ${this.serial} exec-out screencap -p")

                process.inputStream
            } catch (e: Exception) {
                null
            }
        }

        DeviceType.IOS -> {
            null
        }
    }
}