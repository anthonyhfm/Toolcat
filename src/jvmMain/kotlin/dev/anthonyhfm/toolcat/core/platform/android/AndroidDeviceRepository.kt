package dev.anthonyhfm.toolcat.core.platform.android

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import dadb.Dadb
import dev.anthonyhfm.toolcat.core.platform.DeviceRepository

object AndroidDeviceRepository: DeviceRepository<AndroidDevice, AndroidDevice> {
    override var devices: MutableState<List<AndroidDevice>> = mutableStateOf(listOf())
    override var emulators: MutableState<List<AndroidDevice>> = mutableStateOf(listOf())

    private var previousDeviceCount: Int = 0
    private var previousEmulatorCount: Int = 0

    override fun fetchDevices() {
        try {
            val newDevices = Dadb.list()
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

            if (newDevices.size != previousDeviceCount) {
                devices.value = newDevices
                previousDeviceCount = newDevices.size
            }
        } catch (ex: Exception) {
            println(ex.localizedMessage)
        }
    }

    override fun fetchEmulators() {
        try {
            val newEmulators = Dadb.list()
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

            if (newEmulators.size != previousEmulatorCount) {
                emulators.value = newEmulators
                previousEmulatorCount = newEmulators.size
            }
        } catch (ex: Exception) {
            println(ex.localizedMessage)
        }
    }
}
