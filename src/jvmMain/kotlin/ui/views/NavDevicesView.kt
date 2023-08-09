package ui.views

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.*
import mobile.*
import ui.components.NoDeviceFound
import ui.components.Tooltip
import ui.dialogs.DeviceInformationDialog
import ui.dialogs.DeviceQuickActionsDialog

@Composable
fun DeviceListItem(mobileDevice: MobileDevice) {
    var showInformationDialog by remember { mutableStateOf(false) }
    var showQuickActionsDialog by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1F))
            .fillMaxWidth(0.9F)
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
            when (mobileDevice.deviceType) {
                DeviceType.ANDROID -> {
                    Icon(
                        painter = painterResource("icons/adb.svg"),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.background
                    )
                }

                DeviceType.IOS -> {
                    Icon(
                        painter = painterResource("icons/apple.svg"),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.background,
                        modifier = Modifier.height(18.dp)
                    )
                }
            }


        }

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = mobileDevice.getName(),
            fontWeight = FontWeight.Light,
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.onBackground
        )

        mobileDevice.serial?.let {
            Text(
                text = it,
                fontWeight = FontWeight.Light,
                fontStyle = FontStyle.Italic,
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.8F)
            )
        }

        Spacer(modifier = Modifier.weight(1F))

        Row {

            Tooltip(tip = "Quick Actions") {
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
                    mobileDevice = mobileDevice,
                    onClose = {
                        showQuickActionsDialog = false
                    }
                )
            }

            Tooltip(tip = "Device Information") {
                IconButton(
                    onClick = {
                        showInformationDialog = true
                    },
                    content = {
                        Icon(
                            imageVector = Icons.Default.Info,
                            contentDescription = "Information about the Device",
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    }
                )
            }

            if (showInformationDialog) {
                DeviceInformationDialog(
                    mobileDevice = mobileDevice,
                    onClose = {
                        showInformationDialog = false
                    }
                )
            }
        }
    }
}

@Composable
fun DeviceList(deviceList: List<MobileDevice>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(deviceList) {
            DeviceListItem(it)
        }
    }
}

@OptIn(DelicateCoroutinesApi::class)
@Composable
fun DevicesView() {
    var deviceList: List<MobileDevice> by remember { mutableStateOf(listOf()) }

    LaunchedEffect(Dispatchers.IO) {
        while (true) {
            deviceList = MobileDeviceRepository.deviceList
            delay(10)
        }
    }

    if (deviceList.isEmpty()) {
        NoDeviceFound()
    }
    else {
        DeviceList(deviceList)
    }
}
