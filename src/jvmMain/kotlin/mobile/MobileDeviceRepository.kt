package mobile

import java.io.BufferedReader
import java.io.InputStreamReader

object MobileDeviceRepository {
    fun getConnectedIOSDevices(): List<MobileDevice> {
        var outputList: List<MobileDevice> = listOf()

        val process: Process = Runtime.getRuntime().exec("idevice_id -l")
        process.waitFor()

        var commandLineOutputLines = BufferedReader(InputStreamReader(process.inputStream)).readLines()

        for (line in commandLineOutputLines) {
            if (line.isEmpty() || line.isBlank()) {
                continue
            }

            outputList = outputList.plus(
                MobileDevice(
                    uuid = line,
                    deviceType = DeviceType.IOS
                )
            )
        }

        return outputList
    }

    fun getConnectedAndroidDevices(): List<MobileDevice> {
        var outputList: List<MobileDevice> = listOf()

        val process: Process = Runtime.getRuntime().exec("adb devices -l")
        process.waitFor()

        var commandLineOutputLines = BufferedReader(InputStreamReader(process.inputStream)).readLines()

        for (i in 0 .. commandLineOutputLines.indexOf("List of devices attached") + 1) {
            commandLineOutputLines = commandLineOutputLines.drop(i)
        }

        for (line in commandLineOutputLines) {
            if (line.isEmpty() || line.isBlank()) {
                continue
            }

            val deviceRegex = Regex("""^(\w+)\s+device\b""")
            val productRegex = Regex("""product:(\w+)""")
            val modelRegex = Regex("""model:(\w+)""")

            val deviceMatchResult = deviceRegex.find(line)
            val productMatchResult = productRegex.find(line)
            val modelMatchResult = modelRegex.find(line)

            outputList = outputList.plus(
                MobileDevice(
                    serial = deviceMatchResult?.groupValues?.get(1),
                    product = productMatchResult?.groupValues?.get(1),
                    model = modelMatchResult?.groupValues?.get(1),
                    deviceType = DeviceType.ANDROID
                )
            )
        }

        return outputList
    }

    fun getConnectedDevices(): List<MobileDevice> {
        var outputList: List<MobileDevice> = listOf()

        getConnectedAndroidDevices().forEach {
            outputList = outputList.plus(it)
        }

        getConnectedIOSDevices().forEach {
            outputList = outputList.plus(it)
        }

        return outputList
    }
}