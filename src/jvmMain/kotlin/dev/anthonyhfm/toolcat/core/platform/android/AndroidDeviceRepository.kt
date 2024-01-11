package dev.anthonyhfm.toolcat.core.platform.android

import dev.anthonyhfm.toolcat.core.platform.DeviceRepository
import dev.anthonyhfm.toolcat.core.utils.Shell
import java.io.BufferedReader
import java.io.InputStreamReader

object AndroidDeviceRepository: DeviceRepository<AndroidDevice, AndroidDevice> {
    override var connectedDeviceList: MutableList<AndroidDevice> = mutableListOf()
    override var simulatedDeviceList: MutableList<AndroidDevice> = mutableListOf()

    override fun fetchConnectedDevices() {
        var outputList: List<AndroidDevice> = listOf()
        var commandLineOutputLines = Shell.getResponseLines("adb devices -l").toList()

        for (i in 0 .. commandLineOutputLines.indexOf("List of devices attached") + 1) {
            commandLineOutputLines = commandLineOutputLines.drop(i)
        }

        for (line in commandLineOutputLines) {
            if (line.isEmpty() || line.isBlank() )
                continue

            val deviceRegex = Regex("""^(\w+(-\d+)?)\s+device\b""")

            deviceRegex.find(line)?.let {
                if (it.value.contains("emulator")) return@let

                outputList = outputList.plus(
                    AndroidDevice(serial = it.value)
                )
            }
        }

        connectedDeviceList = outputList.toMutableList()
    }

    override fun fetchSimulatedDevices() {
        var outputList: List<AndroidDevice> = listOf()
        var commandLineOutputLines = Shell.getResponseLines("adb devices -l").toList()

        for (i in 0 .. commandLineOutputLines.indexOf("List of devices attached") + 1) {
            commandLineOutputLines = commandLineOutputLines.drop(i)
        }

        for (line in commandLineOutputLines) {
            if (line.isEmpty() || line.isBlank() )
                continue

            val deviceRegex = Regex("""^(\w+(-\d+)?)\s+device\b""")
            deviceRegex.find(line)?.let {
                if (!it.value.contains("emulator")) return@let

                outputList = outputList.plus(
                    AndroidDevice(serial = it.value)
                )
            }
        }

        connectedDeviceList = outputList.toMutableList()
    }
}
