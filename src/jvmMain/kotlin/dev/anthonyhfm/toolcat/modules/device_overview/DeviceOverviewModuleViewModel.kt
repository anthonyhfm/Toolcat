package dev.anthonyhfm.toolcat.modules.device_overview

import androidx.compose.runtime.Composable
import dev.anthonyhfm.toolcat.core.module.ModuleViewModel

object DeviceOverviewModuleViewModel : ModuleViewModel {
    override val name: String = "Devices"
    override val iconResource: String = "icons/smartphone_filled.svg"

    @Composable
    override fun ModuleView() {

    }
}
