package dev.anthonyhfm.toolcat.core.platform.android

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import dadb.Dadb
import dev.anthonyhfm.toolcat.core.platform.DeviceRepository

object AndroidDeviceRepository: DeviceRepository<AndroidDevice, AndroidDevice> {
    override var devices: MutableState<List<AndroidDevice>> = mutableStateOf(listOf())
    override var emulators: MutableState<List<AndroidDevice>> = mutableStateOf(listOf())

    override fun fetchDevices() {
        try {
            devices.value = Dadb.list()
                .filter {
                    !it.toString().contains("emulator")
                }
                .map {
                    AndroidDevice(
                        serial = it.toString(),
                        emulator = false,
                        adb = it
                    )
                }
        } catch (ex: Exception) {
            println(ex.localizedMessage)
        }
    }

    override fun fetchEmulators() {
        try {
            emulators.value = Dadb.list()
                .filter {
                    it.toString().contains("emulator")
                }
                .map {
                    AndroidDevice(
                        serial = it.toString(),
                        emulator = true,
                        adb = it
                    )
                }
        } catch (ex: Exception) {
            println(ex.localizedMessage)
        }
    }
}
