package mobile.firmware

import mobile.DeviceType
import mobile.MobileDevice
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
            if (this.isEmulator) {
                // Command: xcrun simctl listapps ${this.udid}

                val process: Process = Runtime.getRuntime().exec("xcrun simctl listapps ${this.udid}")
                process.waitFor()

                val lines = BufferedReader(InputStreamReader(process.inputStream)).readLines()

                var appTitle: String? = null
                var appBundleID: String? = null

                lines.forEach { line ->
                    val titleRegex = Regex("CFBundleDisplayName = (.+?);")
                    val bundleRegex = Regex("CFBundleIdentifier = \"(.+?)\";")

                    titleRegex.find(line.trimIndent())?.let {
                        appTitle = it.groupValues[1]
                    }

                    bundleRegex.find(line.trimIndent())?.let {
                        appBundleID = it.groupValues[1]
                    }

                    if (appBundleID != null && appTitle != null) {
                        appList = appList.plus(
                            MobileApplication(
                                appTitle!!,
                                appBundleID!!
                            )
                        )

                        appBundleID = null
                        appTitle = null
                    }
                }
            }
            else {
                val process: Process = Runtime.getRuntime().exec("ideviceinstaller -u ${this.udid} -l")
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
    }

    return appList.toTypedArray()
}

fun MobileDevice.launchApplication(mobileApplication: MobileApplication) {
    when (this.deviceType) {
        DeviceType.ANDROID -> {
            Runtime.getRuntime().exec("adb -s ${this.serial} shell monkey -p ${mobileApplication.id} 1")
        }

        DeviceType.IOS -> {
            if (this.isEmulator) {
                Runtime.getRuntime().exec("xcrun simctl launch ${this.udid} ${mobileApplication.id}")
            }
            else {
                TODO("It is currently not possible to launch a application on your iOS Device using Toolcat")
            }
        }
    }
}

fun MobileDevice.installApplication(path: String) {
    when (this.deviceType) {
        DeviceType.ANDROID -> {
            Runtime.getRuntime().exec("adb -s ${this.serial} install $path")
        }

        DeviceType.IOS -> TODO("Installing Applications is currently not supported with iOS Devices")
    }
}

fun MobileDevice.clearApplicationData(mobileApplication: MobileApplication) {
    when (this.deviceType) {
        DeviceType.ANDROID -> {
            Runtime.getRuntime().exec("adb -s ${this.serial} shell pm clear ${mobileApplication.id}")
        }

        DeviceType.IOS -> {
            TODO("Clearing Application Data is currently not supported with iOS Devices")
        }
    }
}

fun MobileDevice.uninstallApplication(mobileApplication: MobileApplication) {
    when (this.deviceType) {
        DeviceType.ANDROID -> {
            Runtime.getRuntime().exec("adb -s ${this.serial} uninstall ${mobileApplication.id}")
        }

        DeviceType.IOS -> {
            if (this.isEmulator) {
                Runtime.getRuntime().exec("xcrun simctl uninstall ${this.udid} ${mobileApplication.id}")
            }
            else {
                TODO("Uninstalling Applications is currently not supported with iOS Devices")
            }
        }
    }
}