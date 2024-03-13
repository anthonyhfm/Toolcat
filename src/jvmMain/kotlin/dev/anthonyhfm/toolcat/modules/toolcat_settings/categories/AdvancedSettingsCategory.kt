package dev.anthonyhfm.toolcat.modules.toolcat_settings.categories

import dev.anthonyhfm.kit.desktop.toasts.Toast
import dev.anthonyhfm.toolcat.core.utils.GlobalSettings
import dev.anthonyhfm.toolcat.modules.toolcat_settings.presets.ButtonSetting
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
        ),
        ButtonSetting(
            displayText = "Test the Toast API",
            buttonText = "Send Toast",
            onButtonClick = {
                Toast.infoToast("Made with \uD83D\uDC9A by Anthony Hofmeister")
            }
        )
    )
)