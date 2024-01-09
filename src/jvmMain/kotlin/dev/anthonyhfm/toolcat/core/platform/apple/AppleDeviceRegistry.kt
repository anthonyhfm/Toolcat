package dev.anthonyhfm.toolcat.core.platform.apple

object AppleDeviceRegistry {
    var deviceList: MutableList<AppleDevice> = mutableListOf()
        private set

    suspend fun scanDevices() {

    }
}
