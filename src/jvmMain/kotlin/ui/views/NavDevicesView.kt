package ui.views

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.TooltipArea
import androidx.compose.foundation.TooltipPlacement
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import mobile.MobileDevice
import mobile.MobileDeviceRepository
import mobile.getManufacturerName
import mobile.getProductName
import ui.components.Tooltip

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DeviceListItem(mobileDevice: MobileDevice) {
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
            Icon(painter = painterResource("icons/adb.svg"), contentDescription = "ADB Icon", tint = MaterialTheme.colorScheme.background)
        }

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = mobileDevice.getManufacturerName() + " " + mobileDevice.getProductName(),
            fontWeight = FontWeight.Light,
            fontSize = 18.sp
        )

        mobileDevice.deviceName?.let {
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
                        // TODO: Show Quick Actions Dialog
                    },
                    content = {
                        Icon(painterResource("icons/bolt.svg"), "Quick Actions for device settings and other stuffs")
                    }
                )
            }

            Tooltip(tip = "Device Information") {
                IconButton(
                    onClick = {
                        // TODO: Show Information Dialog
                    },
                    content = {
                        Icon(Icons.Default.Info, "Information about the Device")
                    }
                )
            }

            Tooltip(tip = "Settings") {
                IconButton(
                    onClick = {
                        // TODO: Show Settings Dialog
                    },
                    content = {
                        Icon(Icons.Default.Settings, "Device Settings")
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

        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(deviceList) {
            DeviceListItem(it)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoDevicesFoundView(onRefresh: () -> Unit) {
    Scaffold(
        floatingActionButton = {
            ExtendedFloatingActionButton(
                icon = { Icon(Icons.Default.Refresh, contentDescription = "Icon for the Device List Refresh FAB") },
                text = {
                    Text("Refresh")
                },
                onClick = { onRefresh() }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),

            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource("icons/mobile_off.svg"),
                contentDescription = "No Mobile",
                modifier = Modifier
                    .size(96.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "No devices found.",
                fontWeight = FontWeight.SemiBold,
                fontSize = 28.sp
            )

            Spacer(modifier = Modifier.height(36.dp))

            Row(
                Modifier.fillMaxWidth(0.75F)
            ) {
                Text(
                    text = "If there is a device connected that should be listed here try pressing on the Refresh button. If that still does not fix the issue, try reconnecting the device and press the Refresh button again.",
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
fun DevicesView() {
    var deviceList: List<MobileDevice> by remember {
        mutableStateOf(
            MobileDeviceRepository().getConnectedDevices()
        )
    }

    if (deviceList.isEmpty()) {
        NoDevicesFoundView(onRefresh = {
            deviceList = MobileDeviceRepository().getConnectedDevices()
        })
    }
    else {
        DeviceList(deviceList)
    }
}
