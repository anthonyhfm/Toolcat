package mobile.hardware

import mobile.DeviceType
import mobile.MobileDevice
import mobile.getProductName
import java.io.BufferedReader
import java.io.InputStreamReader

fun MobileDevice.getBatteryLevel(): Float {
    return when (this.deviceType) {
        DeviceType.ANDROID -> {
            val process: Process = Runtime.getRuntime().exec("adb -s ${this.serial} shell dumpsys battery get level")
            process.waitFor()

            BufferedReader(InputStreamReader(process.inputStream)).readLine().toFloat() / 100
        }

        else -> 0F
    }
}