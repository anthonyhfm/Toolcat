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
    val udid: String? = null,
    val isEmulator: Boolean = false
) {
    var deviceName: String? = null
    var deviceProduct: String? = null
    var deviceManufacturer: String? = null
}

fun MobileDevice.getProductName(): String {
    when (this.deviceType) {
        DeviceType.ANDROID -> {
            if (this.isEmulator) {
                return "Emulator"
            } else {
                if (this.deviceProduct != null) {
                    return this.deviceProduct!!
                }

                val process: Process = Runtime.getRuntime().exec("adb -s ${this.serial} shell getprop ro.product.model")
                process.waitFor()

                this.deviceProduct = BufferedReader(InputStreamReader(process.inputStream)).readLine()

                return this.deviceProduct!!
            }
        }

        DeviceType.IOS -> {
            if (this.deviceProduct != null) {
                return this.deviceProduct!!
            }

            if (this.isEmulator) {
                this.deviceProduct = "Simulator"

                return this.deviceProduct!!
            }

            val process: Process = Runtime.getRuntime().exec("ideviceinfo -u ${this.udid} -k ProductType")
            process.waitFor()

            this.deviceProduct = BufferedReader(InputStreamReader(process.inputStream)).readLine()

            return this.deviceProduct!!
        }
    }
}

fun MobileDevice.getManufacturerName(): String {
    when (this.deviceType) {
        DeviceType.ANDROID -> {
            if (this.isEmulator) {
                return "AVD"
            }
            else {
                if (this.deviceManufacturer != null) {
                    return this.deviceManufacturer!!
                }

                val process: Process = Runtime.getRuntime().exec("adb -s ${this.serial} shell getprop ro.product.manufacturer")
                process.waitFor()

                this.deviceManufacturer =  BufferedReader(InputStreamReader(process.inputStream)).readLine()

                return this.deviceManufacturer!!
            }
        }

        DeviceType.IOS -> return "Apple"
    }
}

fun MobileDevice.getName(): String {
    when (this.deviceType) {
        DeviceType.ANDROID -> {
            return getManufacturerName() + " " + getProductName()
        }

        DeviceType.IOS -> {
            if (this.deviceName != null) {
                return this.deviceName!!
            }

            val process: Process = Runtime.getRuntime().exec("ideviceinfo -u ${this.udid} -k DeviceName")
            process.waitFor()

            this.deviceName = BufferedReader(InputStreamReader(process.inputStream)).readLine()

            return this.deviceName!!
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
            val process: Process = Runtime.getRuntime().exec("ideviceinfo -u ${this.udid} -k ProductVersion")
            process.waitFor()

            BufferedReader(InputStreamReader(process.inputStream)).readLine()
        }
    }
}