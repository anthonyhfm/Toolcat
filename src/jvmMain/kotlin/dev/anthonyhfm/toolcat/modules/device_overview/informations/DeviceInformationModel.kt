package dev.anthonyhfm.toolcat.modules.device_overview.informations

data class DeviceInformation(
    val title: String,
    val text: String
)

data class DeviceInformationCluster(
    val title: String,
    val information: List<DeviceInformation>
)
