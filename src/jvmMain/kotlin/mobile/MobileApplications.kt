package mobile

import mobile.hardware.getBatteryLevel
import java.io.BufferedReader
import java.io.InputStreamReader

data class MobileApplication(
    val name: String,
    val id: String
)

fun MobileDevice.getApplications(): Array<MobileApplication> {
    var appList: List<MobileApplication> = listOf()

    when (this.deviceType) {
        DeviceType.ANDROID -> {
            val process: Process = Runtime.getRuntime().exec("adb -s ${this.serial} shell cmd package list packages -3")
            process.waitFor()

            val lines = BufferedReader(InputStreamReader(process.inputStream)).readLines()

            lines.forEach { line ->
                appList = appList.plus(
                    MobileApplication(
                        name = "Unknown",
                        id = line.removePrefix("package:")
                    )
                )
            }
        }

        DeviceType.IOS -> {
            val process: Process = Runtime.getRuntime().exec("ideviceinstaller -u ${this.uuid} -l")
            process.waitFor()

            val lines = BufferedReader(InputStreamReader(process.inputStream)).readLines()

            lines.forEach { line ->
                if (lines.indexOf(line) != 0) {
                    val lineSplits = line.split(",")

                    val bundleID = lineSplits[0]

                    val bundleName  = lineSplits[2]
                        .trimIndent()
                        .trimStart('"')
                        .trimEnd('"')

                    appList = appList.plus(
                        MobileApplication(
                            name = bundleName,
                            id = bundleID
                        )
                    )
                }
            }
        }
    }

    return appList.toTypedArray()
}