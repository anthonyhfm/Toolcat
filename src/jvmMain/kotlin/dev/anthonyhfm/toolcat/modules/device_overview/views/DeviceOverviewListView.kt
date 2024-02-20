package dev.anthonyhfm.toolcat.modules.device_overview.views

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import dev.anthonyhfm.toolcat.core.platform.android.AndroidDevice
import dev.anthonyhfm.toolcat.core.platform.android.AndroidDeviceRepository
import dev.anthonyhfm.toolcat.core.platform.apple.AppleDevice
import dev.anthonyhfm.toolcat.core.platform.apple.AppleDeviceRepository
import dev.anthonyhfm.toolcat.core.platform.apple.SimulatedAppleDevice
import dev.anthonyhfm.toolcat.main.theme.Inter
import dev.anthonyhfm.toolcat.main.views.VerticalScrollColumn

@Composable
internal fun DeviceOverviewListView() {
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

    AnimatedVisibility(devicesAvailable) {
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

    AnimatedVisibility(!devicesAvailable) {
        EmptyDeviceRepositories()
    }
}
