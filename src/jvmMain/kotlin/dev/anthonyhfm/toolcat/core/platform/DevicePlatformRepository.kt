package dev.anthonyhfm.toolcat.core.platform

import androidx.compose.runtime.MutableState

interface DeviceRepository<Real, Simulator> {
    var devices: MutableState<List<Real>>
    var emulators: MutableState<List<Simulator>>

    fun fetchDevices()
    fun fetchEmulators()
}
