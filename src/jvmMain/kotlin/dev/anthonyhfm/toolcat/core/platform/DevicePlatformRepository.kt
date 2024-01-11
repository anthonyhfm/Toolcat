package dev.anthonyhfm.toolcat.core.platform

interface DeviceRepository<Real, Simulator> {
    var connectedDeviceList: MutableList<Real>
    var simulatedDeviceList: MutableList<Simulator>

    fun fetchConnectedDevices()
    fun fetchSimulatedDevices()
}
