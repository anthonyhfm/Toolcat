package settings

import androidx.compose.runtime.Composable
import utils.OperatingSystem

interface SettingsViewModel {
    val osSupport: List<OperatingSystem>
        get() = listOf(
            OperatingSystem.WINDOWS,
            OperatingSystem.MACOS,
            OperatingSystem.LINUX,
            OperatingSystem.UNKNOWN,
        )

    @Composable
    fun content()
}