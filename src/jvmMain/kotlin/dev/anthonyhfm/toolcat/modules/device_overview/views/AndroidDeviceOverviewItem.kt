package dev.anthonyhfm.toolcat.modules.device_overview.views

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.TooltipArea
import androidx.compose.foundation.TooltipPlacement
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.anthonyhfm.toolcat.core.platform.android.AndroidDevice
import dev.anthonyhfm.toolcat.core.platform.android.system.name
import dev.anthonyhfm.toolcat.main.theme.Inter
import dev.anthonyhfm.toolcat.modules.device_overview.dialogs.DeviceInfoDialog
import dev.anthonyhfm.toolcat.modules.device_overview.dialogs.DeviceQuickActionsDialog

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun AndroidDeviceOverviewItem(androidDevice: AndroidDevice) {
    var showInformationDialog by remember { mutableStateOf(false) }
    var showQuickActionsDialog by remember { mutableStateOf(false) }

    val deviceName: String by remember { mutableStateOf(androidDevice.name) }

    Row(
        modifier = Modifier
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1F))
            .fillMaxWidth()
            .height(60.dp)
            .padding(10.dp),

        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primary)
                .aspectRatio(1F / 1F)
                .fillMaxSize(),

            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource("icons/adb.svg"),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.background
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = deviceName,
            fontWeight = FontWeight.Medium,
            fontSize = 18.sp,
            fontFamily = Inter,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.weight(1F))

        Row {
            TooltipArea(
                tooltip = {
                    Text(
                        text = "Device Actions",
                        fontFamily = Inter,
                        color = MaterialTheme.colorScheme.background,
                        modifier = Modifier
                            .offset(y = 4.dp)
                            .clip(RoundedCornerShape(4.dp))
                            .background(MaterialTheme.colorScheme.onBackground.copy(0.9f))
                            .padding(horizontal = 12.dp, vertical = 6.dp)
                    )
                },
                tooltipPlacement = TooltipPlacement.ComponentRect()
            ) {
                IconButton(
                    onClick = {
                        showQuickActionsDialog = true
                    },
                    content = {
                        Icon(
                            painter = painterResource("icons/bolt.svg"),
                            contentDescription = "Quick Actions for device settings and other stuffs",
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    }
                )
            }

            if (showQuickActionsDialog) {
                DeviceQuickActionsDialog(
                    device = androidDevice,
                    onClose = {
                        showQuickActionsDialog = false
                    }
                )
            }

            TooltipArea(
                tooltip = {
                    Text(
                        text = "Device Information",
                        fontFamily = Inter,
                        color = MaterialTheme.colorScheme.background,
                        modifier = Modifier
                            .offset(y = 4.dp)
                            .clip(RoundedCornerShape(4.dp))
                            .background(MaterialTheme.colorScheme.onBackground.copy(0.9f))
                            .padding(horizontal = 12.dp, vertical = 6.dp)
                    )
                },
                tooltipPlacement = TooltipPlacement.ComponentRect()
            ) {
                IconButton(
                    onClick = {
                        showInformationDialog = true
                    },
                    content = {
                        Icon(
                            imageVector = Icons.Outlined.Info,
                            contentDescription = "Information about the Device",
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    }
                )
            }

            if (showInformationDialog) {
                DeviceInfoDialog(
                    device = androidDevice,
                    onClose = {
                        showInformationDialog = false
                    }
                )
            }
        }
    }
}
