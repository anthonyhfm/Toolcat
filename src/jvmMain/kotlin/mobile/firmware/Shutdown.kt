package mobile.firmware

import mobile.DeviceType
import mobile.MobileDevice

fun MobileDevice.deviceShutdown() {
    when (this.deviceType) {
        DeviceType.ANDROID -> {
            Runtime.getRuntime().exec("adb reboot -p ${this.serial}")
        }

        DeviceType.IOS -> {
            if (this.isEmulator) {
                Runtime.getRuntime().exec("xcrun simctl shutdown ${this.udid}")
            }
            else {
                TODO("Device Shutdown for physical iOS Devices is currently not supported")
            }
        }
    }
}

fun MobileDevice.deviceReboot() {
    when (this.deviceType) {
        DeviceType.ANDROID -> {
            Runtime.getRuntime().exec("adb reboot -s ${this.serial}")
        }

        DeviceType.IOS -> {
            if (this.isEmulator) {
                Runtime.getRuntime().exec("xcrun simctl shutdown ${this.udid} && xcrun simctl boot ${this.udid}")
            }
            else {
                TODO("Device Reboot for physical iOS Devices is currently not supported")
            }
        }
    }
}