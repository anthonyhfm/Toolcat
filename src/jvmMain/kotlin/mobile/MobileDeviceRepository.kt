package mobile

import kotlinx.coroutines.yield
import java.io.BufferedReader
import java.io.InputStreamReader

class MobileDeviceRepository {
    fun getConnectedDevices(): List<MobileDevice> {
        var outputList: List<MobileDevice> = listOf()

        val pingprocess: Process = Runtime.getRuntime().exec("adb devices -l")
        pingprocess.waitFor()

        var commandLineOutputLines = BufferedReader(InputStreamReader(pingprocess.inputStream)).readLines()

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
                    deviceName = deviceMatchResult?.groupValues?.get(1),
                    product = productMatchResult?.groupValues?.get(1),
                    model = modelMatchResult?.groupValues?.get(1)
                )
            )
        }

        return outputList
    }
}