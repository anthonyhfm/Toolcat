package mobile

import java.io.BufferedReader
import java.io.InputStreamReader

enum class DeviceType {
    IOS,
    ANDROID
}

data class MobileDevice(
    val deviceType: DeviceType = DeviceType.ANDROID,
    val serial: String? = null,
    val product: String? = null,
    val model: String? = null,
    val uuid: String? = null
)

fun MobileDevice.getProductName(): String {
    return when (this.deviceType) {
        DeviceType.ANDROID -> {
            val process: Process = Runtime.getRuntime().exec("adb -s ${this.serial} shell getprop ro.product.model")
            process.waitFor()

            return BufferedReader(InputStreamReader(process.inputStream)).readLine()
        }

        DeviceType.IOS -> {
            val process: Process = Runtime.getRuntime().exec("ideviceinfo -u ${this.uuid} -k ProductType")
            process.waitFor()

            BufferedReader(InputStreamReader(process.inputStream)).readLine()
        }
    }
}

fun MobileDevice.getManufacturerName(): String {
    return when (this.deviceType) {
        DeviceType.ANDROID -> {
            val process: Process = Runtime.getRuntime().exec("adb -s ${this.serial} shell getprop ro.product.manufacturer")
            process.waitFor()

            return BufferedReader(InputStreamReader(process.inputStream)).readLine()
        }

        DeviceType.IOS -> "Apple"
    }
}

fun MobileDevice.getName(): String {
    return when (this.deviceType) {
        DeviceType.ANDROID -> {
            getManufacturerName() + " " + getProductName()
        }

        DeviceType.IOS -> {
            val process: Process = Runtime.getRuntime().exec("ideviceinfo -u ${this.uuid} -k DeviceName")
            process.waitFor()

            BufferedReader(InputStreamReader(process.inputStream)).readLine()
        }
    }
}

fun MobileDevice.getSystemVersion(): String {
    return when (this.deviceType) {
        DeviceType.ANDROID -> {
            val systemVersionProcess: Process = Runtime.getRuntime().exec("adb -s ${this.serial} shell getprop ro.build.version.release")
            systemVersionProcess.waitFor()
            val systemVersion = BufferedReader(InputStreamReader(systemVersionProcess.inputStream)).readLine()

            val apiLevelProcess: Process = Runtime.getRuntime().exec("adb -s ${this.serial} shell getprop ro.build.version.sdk")
            apiLevelProcess.waitFor()
            val apiLevel = BufferedReader(InputStreamReader(apiLevelProcess.inputStream)).readLine()

            "$systemVersion (API $apiLevel)"
        }

        DeviceType.IOS -> {
            "TBD"
        }
    }
}