package settings

import settings.views.EnableIosSupportSettingsModel

val settingsClusterList: Array<SettingsCluster> = arrayOf(
    SettingsCluster(
        title = "Apple Devices",
        settings = arrayOf(
            EnableIosSupportSettingsModel()
        )
    )
)