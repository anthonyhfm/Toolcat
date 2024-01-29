package dev.anthonyhfm.toolcat.modules.device_overview

import androidx.compose.runtime.Composable
import dev.anthonyhfm.toolcat.modules.device_overview.views.DeviceOverviewListView

@Composable
internal fun DeviceOverviewView(vm: DeviceOverviewModuleViewModel) {
    DeviceOverviewListView()
}
