package dev.anthonyhfm.toolcat.modules.device_overview

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.anthonyhfm.toolcat.core.platform.android.AndroidDevice
import dev.anthonyhfm.toolcat.core.platform.android.AndroidDeviceRepository
import dev.anthonyhfm.toolcat.core.platform.apple.AppleDevice
import dev.anthonyhfm.toolcat.core.platform.apple.AppleDeviceRepository
import dev.anthonyhfm.toolcat.core.platform.apple.SimulatedAppleDevice
import dev.anthonyhfm.toolcat.main.views.VerticalScrollColumn
import dev.anthonyhfm.toolcat.modules.device_overview.views.AndroidDeviceOverviewItem
import dev.anthonyhfm.toolcat.modules.device_overview.views.AppleDeviceOverviewItem
import dev.anthonyhfm.toolcat.modules.device_overview.views.DeviceCollectionView
import dev.anthonyhfm.toolcat.modules.device_overview.views.EmptyDeviceRepositories
import dev.anthonyhfm.toolcat.modules.device_overview.views.SimulatedAppleDeviceOverviewItem

@Composable
internal fun DeviceOverviewView(vm: DeviceOverviewModule) {
    val repositoryLists: Map<String, List<Any>> = mapOf(
        "Android Devices" to AndroidDeviceRepository.devices.value,
        "Android Emulators" to AndroidDeviceRepository.emulators.value,
        "Apple Devices" to AppleDeviceRepository.devices.value,
        "Apple Emulators" to AppleDeviceRepository.emulators.value
    )

    var devicesAvailable = false

    repositoryLists.forEach {
        if (it.value.isNotEmpty()) {
            devicesAvailable = true
        }
    }

    AnimatedVisibility(
        visible = devicesAvailable,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        VerticalScrollColumn(
            modifier = Modifier
                .fillMaxSize(),

            verticalArrangement = Arrangement.spacedBy(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier)

            repositoryLists.forEach { (key, list) ->
                if (list.isNotEmpty()) {
                    when (list[0]) {
                        is AndroidDevice -> {
                            DeviceCollectionView(key) {
                                list.forEach { AndroidDeviceOverviewItem(it as AndroidDevice) }
                            }
                        }

                        is AppleDevice -> {
                            DeviceCollectionView(key) {
                                list.forEach { AppleDeviceOverviewItem(it as AppleDevice) }
                            }
                        }

                        is SimulatedAppleDevice -> {
                            DeviceCollectionView(key) {
                                list.forEach { SimulatedAppleDeviceOverviewItem(it as SimulatedAppleDevice) }
                            }
                        }

                        else -> { println("Unsupported Device in Device Overview") }
                    }
                }
            }

            Spacer(Modifier)
        }
    }

    AnimatedVisibility(
        visible = !devicesAvailable,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        EmptyDeviceRepositories()
    }
}
