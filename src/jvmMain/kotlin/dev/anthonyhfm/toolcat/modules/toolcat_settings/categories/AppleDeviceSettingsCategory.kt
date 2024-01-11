package dev.anthonyhfm.toolcat.modules.toolcat_settings.categories

import dev.anthonyhfm.toolcat.core.utils.GlobalSettings
import dev.anthonyhfm.toolcat.modules.toolcat_settings.presets.ToggleSetting

internal val AppleDeviceSettingsCategory = SettingsCategory(
    name = "Apple Devices",
    settings = arrayOf(
        ToggleSetting(
            displayText = "Experimental iOS Support",
            state = GlobalSettings.supportIOS,
            onValueChange = {
                GlobalSettings.supportIOS.value = it
                GlobalSettings.saveGlobalSettings()
            }
        ),
        ToggleSetting(
            displayText = "Experimental iOS Simulator Support",
            state = GlobalSettings.supportIOSSimulator,
            onValueChange = {
                GlobalSettings.supportIOSSimulator.value = it
                GlobalSettings.saveGlobalSettings()
            }
        )
    )
)
