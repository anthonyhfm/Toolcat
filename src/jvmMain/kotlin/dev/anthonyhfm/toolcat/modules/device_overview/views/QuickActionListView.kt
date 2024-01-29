package dev.anthonyhfm.toolcat.modules.device_overview.views

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.runtime.Composable
import dev.anthonyhfm.toolcat.core.platform.android.AndroidDevice
import dev.anthonyhfm.toolcat.core.platform.apple.AppleDevice
import dev.anthonyhfm.toolcat.core.platform.apple.SimulatedAppleDevice
import dev.anthonyhfm.toolcat.modules.device_overview.actions.QuickActionModel
import dev.anthonyhfm.toolcat.modules.device_overview.actions.android.getQuickActions

@OptIn(ExperimentalLayoutApi::class)
@Composable
internal fun QuickActionListView(device: Any) {
    FlowRow {
        when (device) {
            is AndroidDevice -> {
                getQuickActions(androidDevice = device).forEach {
                    it.Content()
                }
            }

            is AppleDevice -> { TODO("Not implemented") }

            is SimulatedAppleDevice -> { TODO("Not implemented") }

            else -> {

            }
        }
    }
}
