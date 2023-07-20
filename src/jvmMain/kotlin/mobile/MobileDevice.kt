package mobile

import java.io.BufferedReader
import java.io.InputStreamReader

data class MobileDevice(
    val deviceName: String? = null,
    val product: String? = null,
    val model: String? = null
)

fun MobileDevice.getProductName(): String {
    val process: Process = Runtime.getRuntime().exec("adb -s ${this.deviceName} shell getprop ro.product.model")
    process.waitFor()

    return BufferedReader(InputStreamReader(process.inputStream)).readLine()
}

fun MobileDevice.getManufacturerName(): String {
    val process: Process = Runtime.getRuntime().exec("adb -s ${this.deviceName} shell getprop ro.product.manufacturer")
    process.waitFor()

    return BufferedReader(InputStreamReader(process.inputStream)).readLine()
}