package dev.anthonyhfm.toolcat.core.utils

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import java.util.prefs.Preferences

object GlobalSettings {
    var activeRescan: MutableState<Boolean> = mutableStateOf(true)
    var supportIOS: MutableState<Boolean> = mutableStateOf(false)
    var supportIOSSimulator: MutableState<Boolean> = mutableStateOf(false)
    var enableDarkMode: MutableState<Boolean> = mutableStateOf(false)
    var selectedTheme: MutableState<Int> = mutableStateOf(0)
    var useLegacyAppList: MutableState<Boolean> = mutableStateOf(true)
    var advancedSettings: MutableState<Boolean> = mutableStateOf(false)

    fun saveGlobalSettings() {
        Preferences.userRoot().putBoolean("active-rescan", activeRescan.value)
        Preferences.userRoot().putBoolean("ios-support", supportIOS.value)
        Preferences.userRoot().putBoolean("ios-simulator-support", supportIOSSimulator.value)
        Preferences.userRoot().putBoolean("dark-theme", enableDarkMode.value)
        Preferences.userRoot().putBoolean("legacy-app-list", useLegacyAppList.value)
        Preferences.userRoot().putBoolean("experimental-settings", advancedSettings.value)
        Preferences.userRoot().putInt("selected-theme", selectedTheme.value)
    }

    fun loadGlobalSettings() {
        activeRescan.value = Preferences.userRoot().getBoolean("active-rescan", activeRescan.value)
        supportIOS.value = Preferences.userRoot().getBoolean("ios-support", supportIOS.value)
        supportIOSSimulator.value = Preferences.userRoot().getBoolean("ios-simulator-support", supportIOSSimulator.value)
        enableDarkMode.value = Preferences.userRoot().getBoolean("dark-theme", enableDarkMode.value)
        useLegacyAppList.value = Preferences.userRoot().getBoolean("legacy-app-list", useLegacyAppList.value)
        advancedSettings.value = Preferences.userRoot().getBoolean("experimental-settings", advancedSettings.value)
        selectedTheme.value = Preferences.userRoot().getInt("selected-theme", selectedTheme.value)
    }
}
