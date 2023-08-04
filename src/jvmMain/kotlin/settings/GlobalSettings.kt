package settings

import java.util.prefs.Preferences

object GlobalSettings {
    var iosSupportEnabled: Boolean = false
    var iosSimulatorSupportEnabled: Boolean = false
    var enableDarkMode: Boolean = false

    fun saveGlobalSettings() {
        Preferences.userRoot().putBoolean("ios-support", iosSupportEnabled)
        Preferences.userRoot().putBoolean("ios-simulator-support", iosSimulatorSupportEnabled)
        Preferences.userRoot().putBoolean("dark-theme", enableDarkMode)
    }

    fun loadGlobalSettings() {
        iosSupportEnabled = Preferences.userRoot().getBoolean("ios-support", iosSupportEnabled)
        iosSimulatorSupportEnabled = Preferences.userRoot().getBoolean("ios-simulator-support", iosSimulatorSupportEnabled)
        enableDarkMode = Preferences.userRoot().getBoolean("dark-theme", enableDarkMode)
    }
}