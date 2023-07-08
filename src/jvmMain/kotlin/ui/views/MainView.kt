package ui.views

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import mobile.MobileDevice
import mobile.MobileDeviceRepository
import ui.components.DeviceActionsSidebar
import ui.components.DeviceListSidebar

@Composable
fun MainView() {
    val deviceList: List<MobileDevice> = MobileDeviceRepository().getConnectedDevices()
    var selectedMobileDevice: MobileDevice by remember { mutableStateOf(deviceList[0]) }

    Row(
        modifier = Modifier
            .fillMaxSize()
    ) {
        DeviceListSidebar(devices = deviceList) {
            selectedMobileDevice = deviceList[it]
        }

        DeviceActionsSidebar(
            selectedMobileDevice
        )
    }
}