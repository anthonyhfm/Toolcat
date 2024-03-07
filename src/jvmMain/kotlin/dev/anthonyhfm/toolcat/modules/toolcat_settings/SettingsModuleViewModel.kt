package dev.anthonyhfm.toolcat.modules.toolcat_settings

import androidx.compose.runtime.Composable
import dev.anthonyhfm.toolcat.core.module.ToolcatModule
import dev.anthonyhfm.toolcat.modules.toolcat_settings.categories.AppleDeviceSettingsCategory
import dev.anthonyhfm.toolcat.modules.toolcat_settings.categories.GeneralSettingsCategory
import dev.anthonyhfm.toolcat.modules.toolcat_settings.categories.SettingsCategory
import dev.anthonyhfm.toolcat.modules.toolcat_settings.categories.ThemeSettingsCategory

object SettingsModuleViewModel : ToolcatModule {
    override val name: String = "Settings"
    override val iconResource: String = "icons/settings_filled.svg"

    private var settingsCategories: List<SettingsCategory> = listOf(
        GeneralSettingsCategory,
        AppleDeviceSettingsCategory,
        ThemeSettingsCategory
    )

    fun addCategory(category: SettingsCategory) {
        settingsCategories = settingsCategories.plus(category)
    }

    fun getCategories(): Array<SettingsCategory> {
        return settingsCategories.toTypedArray()
    }

    @Composable
    override fun ModuleView() {
        ApplicationSettingsView(this)
    }
}
