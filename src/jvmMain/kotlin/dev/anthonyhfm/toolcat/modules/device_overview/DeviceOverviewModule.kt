package dev.anthonyhfm.toolcat.modules.device_overview

import androidx.compose.runtime.Composable
import dev.anthonyhfm.toolcat.core.module.ToolcatModule

class DeviceOverviewModule : ToolcatModule {
    override val name: String = "Devices"
    override val iconResource: String = "icons/smartphone_filled.svg"
    override val moduleID: String = "toolcat.deviceoverview"

    @Composable
    override fun ModuleView() {
        DeviceOverviewView(this)
    }
}
