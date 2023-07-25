package settings

import settings.views.EnableIosSupport

val settingsClusterList: Array<SettingsCluster> = arrayOf(
    SettingsCluster(
        title = "Apple Devices",
        settings = arrayOf(
            EnableIosSupport()
        )
    )
)