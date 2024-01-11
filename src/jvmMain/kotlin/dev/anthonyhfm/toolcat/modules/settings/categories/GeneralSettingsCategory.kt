package dev.anthonyhfm.toolcat.modules.settings.categories

import androidx.compose.runtime.mutableStateOf
import dev.anthonyhfm.toolcat.core.utils.GlobalSettings
import dev.anthonyhfm.toolcat.modules.settings.presets.ToggleSetting

internal val GeneralSettingsCategory = SettingsCategory(
    name = "General",
    settings = arrayOf(
        ToggleSetting(
            displayText = "Active Device Rescan",
            state = mutableStateOf(true),
            onValueChange = {
                GlobalSettings.activeRescan.value = it
                GlobalSettings.saveGlobalSettings()
            }
        )
    )
)
