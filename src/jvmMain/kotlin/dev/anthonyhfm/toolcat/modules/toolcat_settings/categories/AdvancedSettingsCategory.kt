package dev.anthonyhfm.toolcat.modules.toolcat_settings.categories

import dev.anthonyhfm.toolcat.core.utils.GlobalSettings
import dev.anthonyhfm.toolcat.modules.toolcat_settings.presets.ToggleSetting

internal val AdvancedSettingsCategory = SettingsCategory(
    name = "Advanced Settings",
    settings = arrayOf(
        ToggleSetting(
            displayText = "Use Legacy App List",
            state = GlobalSettings.useLegacyAppList,
            onValueChange = {
                GlobalSettings.useLegacyAppList.value = it
                GlobalSettings.saveGlobalSettings()
            }
        )
    )
)