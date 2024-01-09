package dev.anthonyhfm.toolcat.core.platform.android

object AndroidDeviceRegistry {
    var deviceList: MutableList<AndroidDevice> = mutableListOf()
        private set

    suspend fun scanDevices() {

    }
}
