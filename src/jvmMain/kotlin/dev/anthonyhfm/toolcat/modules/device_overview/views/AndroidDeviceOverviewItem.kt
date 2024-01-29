package dev.anthonyhfm.toolcat.modules.device_overview.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
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
import dev.anthonyhfm.toolcat.modules.device_overview.dialogs.DeviceInfoDialog
import dev.anthonyhfm.toolcat.modules.device_overview.dialogs.DeviceQuickActionsDialog
import ui.components.Tooltip

@Composable
internal fun AndroidDeviceOverviewItem(androidDevice: AndroidDevice) {
    var showInformationDialog by remember { mutableStateOf(false) }
    var showQuickActionsDialog by remember { mutableStateOf(false) }

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
            text = androidDevice.name,
            fontWeight = FontWeight.Light,
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.onBackground
        )

        Text(
            text = androidDevice.serial,
            fontWeight = FontWeight.Light,
            fontStyle = FontStyle.Italic,
            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.8F)
        )

        Spacer(modifier = Modifier.weight(1F))

        Row {
            Tooltip(tip = "Actions") {
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

            Tooltip(tip = "Info") {
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
