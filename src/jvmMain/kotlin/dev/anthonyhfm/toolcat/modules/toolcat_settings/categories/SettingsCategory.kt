package dev.anthonyhfm.toolcat.modules.toolcat_settings.categories

import dev.anthonyhfm.toolcat.modules.toolcat_settings.presets.SettingModel

data class SettingsCategory(
    val name: String,
    val settings: Array<SettingModel>
)
