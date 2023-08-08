package mobile

import kotlinx.coroutines.runBlocking
import mobile.firmware.getScreenshot
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.Exception

fun MobileDevice.openScreenMirror() {
    when (this.deviceType) {
        DeviceType.ANDROID -> {
            Runtime.getRuntime().exec("scrcpy --serial $serial")
        }

        DeviceType.IOS -> return
    }
}