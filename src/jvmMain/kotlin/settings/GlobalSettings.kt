package settings

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import java.util.prefs.Preferences

object GlobalSettings {
    var iosSupportEnabled: Boolean = false
    var iosSimulatorSupportEnabled: Boolean = false
    var enableDarkMode: MutableState<Boolean> = mutableStateOf(false)
    var selectedTheme: MutableState<Int> = mutableStateOf(0)
    var customTitleBar: MutableState<Boolean> = mutableStateOf(false)

    fun saveGlobalSettings() {
        Preferences.userRoot().putBoolean("ios-support", iosSupportEnabled)
        Preferences.userRoot().putBoolean("ios-simulator-support", iosSimulatorSupportEnabled)
        Preferences.userRoot().putBoolean("dark-theme", enableDarkMode.value)
        Preferences.userRoot().putBoolean("custom-title-bar", customTitleBar.value)
        Preferences.userRoot().putInt("selected-theme", selectedTheme.value)
    }

    fun loadGlobalSettings() {
        iosSupportEnabled = Preferences.userRoot().getBoolean("ios-support", iosSupportEnabled)
        iosSimulatorSupportEnabled = Preferences.userRoot().getBoolean("ios-simulator-support", iosSimulatorSupportEnabled)
        enableDarkMode.value = Preferences.userRoot().getBoolean("dark-theme", enableDarkMode.value)
        customTitleBar.value = Preferences.userRoot().getBoolean("custom-title-bar", customTitleBar.value)
        selectedTheme.value = Preferences.userRoot().getInt("selected-theme", selectedTheme.value)
    }
}
