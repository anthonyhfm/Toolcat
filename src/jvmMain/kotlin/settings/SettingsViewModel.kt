package settings

import androidx.compose.runtime.Composable
import utils.OperatingSystem

interface SettingsViewModel {
    val osSupport: List<OperatingSystem>

    @Composable
    fun content()
}