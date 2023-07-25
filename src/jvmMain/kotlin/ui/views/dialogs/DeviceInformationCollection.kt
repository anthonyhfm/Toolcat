package ui.views.dialogs

import mobile.getManufacturerName
import mobile.getProductName
import mobile.getSystemVersion
import mobile.hardware.getBatteryLevel

val deviceInformationList: Array<DeviceInformationData> = arrayOf(
    DeviceInformationData(
        title = "Device Manufacturer",
        fetch = { it.getManufacturerName() }
    ),
    DeviceInformationData(
        title = "Device Model",
        fetch = { it.getProductName() }
    ),
    DeviceInformationData(
        title = "Battery Level",
        fetch = { (it.getBatteryLevel() * 100).toString() }
    ),
    DeviceInformationData(
        title = "System Version",
        fetch = { it.getSystemVersion() }
    ),
)
