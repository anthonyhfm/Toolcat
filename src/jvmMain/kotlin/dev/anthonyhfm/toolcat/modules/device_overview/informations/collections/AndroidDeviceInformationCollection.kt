package dev.anthonyhfm.toolcat.modules.device_overview.informations.collections

import dev.anthonyhfm.toolcat.core.platform.android.AndroidDevice
import dev.anthonyhfm.toolcat.core.platform.android.system.*
import dev.anthonyhfm.toolcat.modules.device_overview.informations.DeviceInformation
import dev.anthonyhfm.toolcat.modules.device_overview.informations.DeviceInformationCluster

fun getInformationClusterList(androidDevice: AndroidDevice): List<DeviceInformationCluster> {
    return listOf(
        DeviceInformationCluster(
            title = "General Information",
            information = listOf(
                DeviceInformation("Manufacturer", androidDevice.manufacturer),
                DeviceInformation("Model", androidDevice.model),
                DeviceInformation("System Version", "Android ${androidDevice.version}"),
                DeviceInformation("SDK Version", "${androidDevice.sdk}")
            )
        ),
        DeviceInformationCluster(
            title = "Hardware Information",
            information = listOf(
                DeviceInformation("Battery Level", "${(androidDevice.batteryLevel * 100).toString().removeSuffix(".0")}%"),
                DeviceInformation("Screen Resolution", "${androidDevice.screenWidth} x ${androidDevice.screenHeight}"),
            )
        ),
    )
}
