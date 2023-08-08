package mobile

import kotlinx.coroutines.DelicateCoroutinesApi
import settings.GlobalSettings
import java.io.BufferedReader
import java.io.InputStreamReader

object MobileDeviceRepository {
    var deviceList: List<MobileDevice> = listOf()

    private fun getIOSSimulators(): List<MobileDevice> {
        var outputList: List<MobileDevice> = listOf()

        val process: Process = Runtime.getRuntime().exec("xcrun simctl list")
        process.waitFor()

        val commandLineOutputLines = BufferedReader(InputStreamReader(process.inputStream)).readLines()

        val devicePattern = Regex("""(.+?) \(""")
        val uuidPattern = Regex("""\(([\w-]+)\)""")

        for (line in commandLineOutputLines) {
            if (line.contains("Booted")) {
                val deviceMatch = devicePattern.find(line.trimIndent())
                val uuidMatch = uuidPattern.find(line.trimIndent())

                if (uuidMatch != null && deviceMatch != null) {
                    val deviceName = deviceMatch.groupValues[1]
                    val uuid = uuidMatch.groupValues[1]

                    val device = MobileDevice(
                        udid = uuid,
                        deviceType = DeviceType.IOS,
                        isEmulator = true
                    )

                    device.deviceName = deviceName

                    outputList = outputList.plus(device)
                }
            }
        }

        return outputList
    }

    private fun getConnectedIOSDevices(): List<MobileDevice> {
        var outputList: List<MobileDevice> = listOf()

        val process: Process = Runtime.getRuntime().exec("idevice_id -l")
        process.waitFor()

        val commandLineOutputLines = BufferedReader(InputStreamReader(process.inputStream)).readLines()

        for (line in commandLineOutputLines) {
            if (line.isEmpty() || line.isBlank()) {
                continue
            }

            outputList = outputList.plus(MobileDevice(
                udid = line,
                deviceType = DeviceType.IOS
            ))
        }

        return outputList
    }

    private fun getConnectedAndroidDevices(): List<MobileDevice> {
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
            
            val deviceRegex = Regex("""^(\w+(-\d+)?)\s+device\b""")
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
                    isEmulator = deviceMatchResult?.groupValues?.get(1)!!.contains("emulator"),
                    deviceType = DeviceType.ANDROID
                )
            )
        }

        return outputList
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun fetchConnectedDevices() {
        var outputList: List<MobileDevice> = listOf()

        getConnectedAndroidDevices().forEach {
            outputList = outputList.plus(it)
        }

        if (GlobalSettings.iosSupportEnabled) {
            getConnectedIOSDevices().forEach {
                outputList = outputList.plus(it)
            }
        }

        if (GlobalSettings.iosSimulatorSupportEnabled) {
            getIOSSimulators().forEach {
                outputList = outputList.plus(it)
            }
        }

        deviceList = outputList
    }
}