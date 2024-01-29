package dev.anthonyhfm.toolcat.modules.device_overview.views

import androidx.compose.runtime.Composable
import dev.anthonyhfm.toolcat.core.platform.android.AndroidDevice
import dev.anthonyhfm.toolcat.core.platform.apple.AppleDevice
import dev.anthonyhfm.toolcat.core.platform.apple.SimulatedAppleDevice

@Composable
private fun BaseDeviceListItem() {

}

@Composable
fun DeviceOverviewListItem(device: AndroidDevice) {

}

@Composable
fun DeviceOverviewListItem(device: AppleDevice) {

}

@Composable
fun DeviceOverviewListItem(device: SimulatedAppleDevice) {

}
