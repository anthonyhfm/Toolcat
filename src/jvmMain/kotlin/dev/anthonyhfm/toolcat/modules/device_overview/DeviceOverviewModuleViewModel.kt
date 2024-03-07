package dev.anthonyhfm.toolcat.modules.device_overview

import androidx.compose.runtime.Composable
import dev.anthonyhfm.toolcat.core.module.ToolcatModule

object DeviceOverviewModuleViewModel : ToolcatModule {
    override val name: String = "Devices"
    override val iconResource: String = "icons/smartphone_filled.svg"

    @Composable
    override fun ModuleView() {
        DeviceOverviewView(this)
    }
}
