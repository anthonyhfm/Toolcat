package dev.anthonyhfm.toolcat.modules.app_overview

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import dev.anthonyhfm.toolcat.core.platform.android.AndroidDevice
import dev.anthonyhfm.toolcat.core.platform.android.AndroidDeviceRepository
import dev.anthonyhfm.toolcat.core.platform.android.system.getAppPackages
import dev.anthonyhfm.toolcat.core.platform.android.system.name
import dev.anthonyhfm.toolcat.core.utils.GlobalSettings
import dev.anthonyhfm.toolcat.main.views.VerticalScrollColumn
import dev.anthonyhfm.toolcat.modules.app_overview.views.*
import dev.anthonyhfm.toolcat.modules.app_overview.views.AppList
import dev.anthonyhfm.toolcat.modules.app_overview.views.LegacyAppList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
internal fun AppOverviewView() {
    var devices: List<AndroidDevice> by remember { mutableStateOf(AndroidDeviceRepository.devices.value + AndroidDeviceRepository.emulators.value) }
    var selections: List<String> by remember { mutableStateOf(devices.map { it.name }) }
    var selectedSerial: String? by remember { mutableStateOf(null) }

    LaunchedEffect(AndroidDeviceRepository.devices.value) {
        devices = AndroidDeviceRepository.devices.value + AndroidDeviceRepository.emulators.value
        selections = devices.map { it.name }
    }

    LaunchedEffect(AndroidDeviceRepository.emulators.value) {
        devices = AndroidDeviceRepository.devices.value + AndroidDeviceRepository.emulators.value
        selections = devices.map { it.name }
    }

    AnimatedVisibility(
        visible = devices.isNotEmpty(),
        enter = fadeIn(),
        exit = fadeOut(),
    ) {
        Column {
            if (selectedSerial != null) {
                AppOverviewHeader(
                    selected = devices.indexOf(devices.find { it.serial == selectedSerial }),
                    onSelectionChanged = {
                        selectedSerial = devices[it].serial
                    },
                    selections = selections
                )
            } else {
                LaunchedEffect(Unit) {
                    selectedSerial = devices[0].serial
                }
            }

            Divider(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth(0.9f)
            )

            devices.find { it.serial == selectedSerial }?.let {
                if (GlobalSettings.useLegacyAppList.value) {
                    LegacyAppList(it)
                } else {
                    AppList()
                }
            }
        }
    }

    AnimatedVisibility(
        visible = devices.isEmpty(),
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        AppOverviewNoDevices()
    }
}