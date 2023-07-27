package settings

import settings.views.IOSSupportSettingsModel

val settingsClusterList: Array<SettingsCluster> = arrayOf(
    SettingsCluster(
        title = "Apple Devices",
        settings = arrayOf(
            IOSSupportSettingsModel()
        )
    )
)