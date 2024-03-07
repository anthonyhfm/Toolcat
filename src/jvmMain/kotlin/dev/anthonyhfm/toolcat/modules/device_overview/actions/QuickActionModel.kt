package dev.anthonyhfm.toolcat.modules.device_overview.actions

import androidx.compose.runtime.Composable

abstract class QuickActionModel <DeviceType> (open val device: DeviceType) {
    @Composable
    open fun Content() { }
}
