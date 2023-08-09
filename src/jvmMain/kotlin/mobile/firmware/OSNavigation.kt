package mobile.firmware

import mobile.DeviceType
import mobile.MobileDevice

fun MobileDevice.navigateBack() {
    when (this.deviceType) {
        DeviceType.ANDROID -> {
            Runtime.getRuntime().exec("adb shell -s ${this.serial} input keyevent 4")
        }

        DeviceType.IOS -> {
            TODO("Navigating back is currently not possible for iOS Devices")
        }
    }
}

fun MobileDevice.navigateToHome() {
    when (this.deviceType) {
        DeviceType.ANDROID -> {
            Runtime.getRuntime().exec("adb shell -s ${this.serial} input keyevent 3")
        }

        DeviceType.IOS -> {
            TODO("Navigating to home is currently not possible for iOS Devices")
        }
    }
}