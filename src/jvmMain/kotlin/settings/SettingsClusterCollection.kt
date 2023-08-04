package settings

import settings.views.CheckForUpdatesSettingsModel
import settings.views.EnableDarkModeSettingsModel
import settings.views.IOSSimulatorSupportSettingsModel
import settings.views.IOSSupportSettingsModel

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
            EnableDarkModeSettingsModel()
        )
    )
)