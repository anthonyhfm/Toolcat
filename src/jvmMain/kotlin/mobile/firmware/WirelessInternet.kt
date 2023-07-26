package mobile.firmware

import mobile.DeviceType
import mobile.MobileDevice
import java.io.BufferedReader
import java.io.InputStreamReader

fun MobileDevice.getWiFiActivated(): Boolean {
    return when (this.deviceType) {
        DeviceType.ANDROID -> {
            val process: Process = Runtime.getRuntime().exec("adb -s ${this.serial} shell settings get global wifi_on")

            BufferedReader(InputStreamReader(process.inputStream)).readLine() == "1"
        }

        DeviceType.IOS -> {
            false
        }
    }
}

fun MobileDevice.enableWifi() {
    when (this.deviceType) {
        DeviceType.ANDROID -> {
            Runtime.getRuntime().exec("adb -s $serial shell svc wifi enable").waitFor()
        }

        DeviceType.IOS -> {
            TODO("Enabling Wi-Fi on Apple Devices has to be done")
        }
    }
}

fun MobileDevice.disableWifi() {
    when (this.deviceType) {
        DeviceType.ANDROID -> {
            Runtime.getRuntime().exec("adb -s $serial shell svc wifi disable").waitFor()
        }

        DeviceType.IOS -> {
            TODO("Disabling Wi-Fi on Apple Devices has to be done")
        }
    }
}
