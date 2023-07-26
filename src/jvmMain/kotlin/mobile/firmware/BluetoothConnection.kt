package mobile.firmware

import mobile.DeviceType
import mobile.MobileDevice
import java.io.BufferedReader
import java.io.InputStreamReader

fun MobileDevice.getBluetoothActivated(): Boolean {
    return when (this.deviceType) {
        DeviceType.ANDROID -> {
            val process: Process = Runtime.getRuntime().exec("adb -s ${this.serial} shell settings get global bluetooth_on")

            BufferedReader(InputStreamReader(process.inputStream)).readLine() == "1"
        }

        DeviceType.IOS -> {
            false
        }
    }
}

fun MobileDevice.enableBluetooth() {
    when (this.deviceType) {
        DeviceType.ANDROID -> {
            Runtime.getRuntime().exec("adb -s $serial shell svc bluetooth enable").waitFor()
        }

        DeviceType.IOS -> {
            TODO("Disabling Bluetooth on Apple Devices has to be done")
        }
    }
}

fun MobileDevice.disableBluetooth() {
    when (this.deviceType) {
        DeviceType.ANDROID -> {
            Runtime.getRuntime().exec("adb -s $serial shell svc bluetooth disable").waitFor()
        }

        DeviceType.IOS -> {
            TODO("Disabling Bluetooth on Apple Devices has to be done")
        }
    }
}