package dev.anthonyhfm.toolcat.modules.toolcat_settings.categories

import dev.anthonyhfm.toolcat.core.utils.GlobalSettings
import dev.anthonyhfm.toolcat.modules.toolcat_settings.extras.ThemePreviewSetting
import dev.anthonyhfm.toolcat.modules.toolcat_settings.presets.ToggleSetting

internal val ThemeSettingsCategory = SettingsCategory(
    name = "Theme",
    settings = arrayOf(
        ToggleSetting(
            displayText = "Dark Mode",
            state = GlobalSettings.enableDarkMode,
            onValueChange = {
                GlobalSettings.enableDarkMode.value = it
                GlobalSettings.saveGlobalSettings()
            }
        ),
        ThemePreviewSetting()
    )
)
