package settings

import java.util.prefs.Preferences

object GlobalSettings {
    var iosSupportEnabled: Boolean = false

    fun saveGlobalSettings() {
        Preferences.userRoot().putBoolean("ios-support", iosSupportEnabled)
    }

    fun loadGlobalSettings() {
        iosSupportEnabled = Preferences.userRoot().getBoolean("ios-support", iosSupportEnabled)
    }
}