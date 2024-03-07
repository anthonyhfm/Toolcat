package dev.anthonyhfm.toolcat.core.platform.apple

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import dev.anthonyhfm.toolcat.core.platform.DeviceRepository

object AppleDeviceRepository : DeviceRepository<AppleDevice, SimulatedAppleDevice> {
    override var devices: MutableState<List<AppleDevice>> = mutableStateOf(listOf())
    override var emulators: MutableState<List<SimulatedAppleDevice>> = mutableStateOf(listOf())

    override fun fetchDevices() {
        TODO("Not yet implemented")
    }

    override fun fetchEmulators() {
        TODO("Not yet implemented")
    }
}
