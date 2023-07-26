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

        DeviceType.IOS -> {
            val process: Process = Runtime.getRuntime().exec("idevicediagnostics -u ${this.uuid} ioregentry AppleSmartBattery")
            process.waitFor()

            val responseLines = BufferedReader(InputStreamReader(process.inputStream)).readLines()

            var outputCapacity: Float = 0F
            var isCapacityLine: Boolean = false

            for (line in responseLines) {
                if (isCapacityLine) {
                    isCapacityLine = false

                    val input = line.trimIndent()
                    val regex = Regex("<integer>(\\d+)</integer>")
                    val matchResult = regex.find(input)

                    if (matchResult != null) {
                        outputCapacity = matchResult.groupValues[1].toInt().toFloat() / 100
                    }
                }

                if(line.trimIndent() == "<key>CurrentCapacity</key>") {
                    isCapacityLine = true
                }
            }

            outputCapacity
        }
    }
}