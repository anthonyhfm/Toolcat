package dev.anthonyhfm.toolcat.modules.toolcat_settings.categories

import androidx.compose.runtime.mutableStateOf
import dev.anthonyhfm.toolcat.core.utils.GlobalSettings
import dev.anthonyhfm.toolcat.modules.toolcat_settings.presets.ToggleSetting

internal val GeneralSettingsCategory = SettingsCategory(
    name = "General",
    settings = arrayOf(
        ToggleSetting(
            displayText = "Active Device Rescan",
            state = GlobalSettings.activeRescan,
            onValueChange = {
                GlobalSettings.activeRescan.value = it
                GlobalSettings.saveGlobalSettings()
            }
        ),
        ToggleSetting(
            displayText = "Show Advanced Settings",
            state = GlobalSettings.advancedSettings,
            onValueChange = {
                GlobalSettings.advancedSettings.value = it
                GlobalSettings.saveGlobalSettings()
            }
        ),
    )
)
