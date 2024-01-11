package dev.anthonyhfm.toolcat.core.platform.apple

import dev.anthonyhfm.toolcat.core.platform.DeviceRepository

object AppleDeviceRepository : DeviceRepository<AppleDevice, SimulatedAppleDevice> {
    override var connectedDeviceList: MutableList<AppleDevice> = mutableListOf()
    override var simulatedDeviceList: MutableList<SimulatedAppleDevice> = mutableListOf()

    override fun fetchConnectedDevices() {
        TODO("Not yet implemented")
    }

    override fun fetchSimulatedDevices() {
        TODO("Not yet implemented")
    }
}
