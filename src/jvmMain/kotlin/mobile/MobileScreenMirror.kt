package mobile

import kotlinx.coroutines.runBlocking
import java.io.BufferedReader
import java.io.InputStreamReader

fun MobileDevice.openScreenMirror() {
    when (this.deviceType) {
        DeviceType.ANDROID -> {
            Runtime.getRuntime().exec("scrcpy --serial $serial")
        }

        DeviceType.IOS -> return
    }
}