package settings

import settings.views.*

val settingsClusterList: Array<SettingsCluster> = arrayOf(
    SettingsCluster(
        title = "General",
        settings = arrayOf(
            CheckForUpdatesSettingsModel()
        )
    ),
    SettingsCluster(
        title = "Apple Devices",
        settings = arrayOf(
            IOSSupportSettingsModel(),
            IOSSimulatorSupportSettingsModel()
        )
    ),
    SettingsCluster(
        title = "Appearance",
        settings = arrayOf(
            EnableDarkModeSettingsModel(),
            ThemeSelectionSettingsModel()
        )
    )
)