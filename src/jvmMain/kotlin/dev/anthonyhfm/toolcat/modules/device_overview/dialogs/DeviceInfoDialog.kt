package dev.anthonyhfm.toolcat.modules.device_overview.dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.anthonyhfm.toolcat.core.platform.android.AndroidDevice
import dev.anthonyhfm.toolcat.core.platform.apple.AppleDevice
import dev.anthonyhfm.toolcat.core.platform.apple.SimulatedAppleDevice
import dev.anthonyhfm.toolcat.main.theme.Inter
import dev.anthonyhfm.toolcat.main.views.Dialog
import dev.anthonyhfm.toolcat.main.views.VerticalScrollColumn
import dev.anthonyhfm.toolcat.modules.device_overview.informations.DeviceInformation
import dev.anthonyhfm.toolcat.modules.device_overview.informations.DeviceInformationCluster
import dev.anthonyhfm.toolcat.modules.device_overview.informations.collections.getInformationClusterList

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun DeviceInfoDialog(device: Any, onClose: () -> Unit) {
    var mouseOnDialog: Boolean by remember { mutableStateOf(false) }

    val informationClusters: List<DeviceInformationCluster> by remember {
        mutableStateOf(
            when (device) {
                is AndroidDevice -> { getInformationClusterList(device) } // as AndroidDevice
                is AppleDevice -> { getInformationClusterList(device) } // as AppleDevice
                is SimulatedAppleDevice -> { getInformationClusterList(device) } // as SimulatedAppleDevice

                else -> { throw Error("This device is not supported by the DeviceInformationDialog") }
            }
        )
    }

    Dialog(
        onClose = {
            onClose()
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.6f))
                .onPointerEvent(PointerEventType.Press) {
                    if (!mouseOnDialog) {
                        onClose()
                    }
                },

            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .width(450.dp)
                    .clip(RoundedCornerShape(32.dp))
                    .background(MaterialTheme.colorScheme.background)
                    .onPointerEvent(PointerEventType.Enter) {
                        mouseOnDialog = true
                    }
                    .onPointerEvent(PointerEventType.Exit) {
                        mouseOnDialog = false
                    }
                    .padding(24.dp)
            ) {
                Text(
                    text = "Device Information",
                    fontFamily = Inter,
                    fontSize = 24.sp,
                    modifier = Modifier.padding(bottom = 16.dp),
                    color = MaterialTheme.colorScheme.onBackground
                )

                VerticalScrollColumn(
                    modifier = Modifier
                        .height(400.dp)
                        .fillMaxWidth(),

                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    informationClusters.forEach {
                        InformationCluster(it)
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),

                    horizontalArrangement = Arrangement.End
                ) {
                    Button(
                        onClick = {
                            onClose()
                        }
                    ) {
                        Text("Close")
                    }
                }
            }
        }
    }
}

@Composable
private fun InformationCluster(informationCluster: DeviceInformationCluster) {
    Column(
        modifier = Modifier
            .padding(end = 16.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = informationCluster.title,
            fontFamily = Inter,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .padding(vertical = 12.dp),
            color = MaterialTheme.colorScheme.onBackground
        )

        informationCluster.information.forEach {
            Information(it)
        }
    }
}

@Composable
private fun Information(deviceInformation: DeviceInformation) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),

        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = deviceInformation.title,
            fontFamily = Inter,
            color = MaterialTheme.colorScheme.onBackground.copy(0.9f)
        )

        Text(
            text = deviceInformation.text,
            fontFamily = Inter,
            modifier = Modifier
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.tertiary.copy(0.9f))
                .padding(horizontal = 16.dp, vertical = 6.dp),
            color = MaterialTheme.colorScheme.onTertiary
        )
    }
}
