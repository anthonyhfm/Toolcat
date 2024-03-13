package dev.anthonyhfm.toolcat.core.platform.android.system

import dev.anthonyhfm.toolcat.core.platform.android.AndroidDevice
import dev.anthonyhfm.toolcat.core.utils.Shell
import java.io.File

fun AndroidDevice.getAppPackages(): List<String> {
    val packageList = adb.shell("cmd package list packages -3")
        .output.trimIndent()
        .lines().map {
            it.removePrefix("package:")
        }

    return packageList
}

fun AndroidDevice.installApp(path: String) {
    val file = File(path)

    adb.install(file)
}

fun AndroidDevice.removeApp(packageID: String) {
    adb.uninstall(packageID)
}

fun AndroidDevice.openApp(packageID: String) {
    adb.shell("monkey -p $packageID 1")
}

fun AndroidDevice.killApp(packageID: String) {
    adb.shell("am force-stop $packageID")
}

fun AndroidDevice.clearAppStorage(packageID: String) {
    adb.shell("pm clear $packageID")
}
