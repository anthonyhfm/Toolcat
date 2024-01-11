package dev.anthonyhfm.toolcat.modules.settings.categories

import dev.anthonyhfm.toolcat.modules.settings.presets.SettingModel

data class SettingsCategory(
    val name: String,
    val settings: Array<SettingModel>
)
