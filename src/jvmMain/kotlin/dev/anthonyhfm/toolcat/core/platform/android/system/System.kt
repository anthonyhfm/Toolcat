package dev.anthonyhfm.toolcat.core.platform.android.system

import dev.anthonyhfm.toolcat.core.platform.android.AndroidDevice

fun AndroidDevice.forceReboot() {
    try {
        adb.shell("reboot")
    } catch (ex: Exception) {
        // An Exception is thrown. Always.
        // This is because the data stream between Toolcat and the device breaks
        // due to the device restarting. So this is pretty normal ig.

        // Nothing to fix here
    }
}

val AndroidDevice.name: String
    get() {
        return if (!emulator) {
            "$manufacturer $model"
        } else { // If the Device is Emulated fetch the AVD Name
            adb.shell("getprop ro.boot.qemu.avd_name").output.trimIndent()
        }
    }

val AndroidDevice.manufacturer: String
    get() {
        return adb.shell("getprop ro.product.manufacturer").output.trimIndent()
    }

val AndroidDevice.model: String
    get() {
        return adb.shell("getprop ro.product.model").output.trimIndent()
    }

val AndroidDevice.version: Int
    get() {
        return adb.shell("getprop ro.system.build.version.release").output.trimIndent().toInt()
    }

val AndroidDevice.sdk: Int
    get() {
        return adb.shell("getprop ro.system.build.version.sdk").output.trimIndent().toInt()
    }

val AndroidDevice.screenWidth: Int
    get() {
        return adb.shell("wm size").output.trimIndent().split(" ").last().split("x")[0].toInt()
    }

val AndroidDevice.screenHeight: Int
    get() {
        return adb.shell("wm size").output.trimIndent().split(" ").last().split("x")[1].toInt()
    }
