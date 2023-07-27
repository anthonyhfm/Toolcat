package settings

import java.util.prefs.Preferences

object GlobalSettings {
    var iosSupportEnabled: Boolean = false
    var iosSimulatorSupportEnabled: Boolean = false

    fun saveGlobalSettings() {
        Preferences.userRoot().putBoolean("ios-support", iosSupportEnabled)
        Preferences.userRoot().putBoolean("ios-simulator-support", iosSimulatorSupportEnabled)
    }

    fun loadGlobalSettings() {
        iosSupportEnabled = Preferences.userRoot().getBoolean("ios-support", iosSupportEnabled)
        iosSupportEnabled = Preferences.userRoot().getBoolean("ios-simulator-support", iosSimulatorSupportEnabled)
    }
}