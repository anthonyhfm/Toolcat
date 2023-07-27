package mobile.firmware

import mobile.DeviceType
import mobile.MobileDevice

fun MobileDevice.openDeepLink(deepLink: String) {
    when (this.deviceType) {
        DeviceType.ANDROID -> {
            Runtime.getRuntime().exec("adb -s ${this.serial} shell am start -a android.intent.action.VIEW -d \"$deepLink\"")
        }

        DeviceType.IOS -> {
            TODO("It is currently not possible to send a deep link to your iOS Device using Toolcat")
        }
    }
}