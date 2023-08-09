package ui.views

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.*
import mobile.*
import mobile.firmware.*
import ui.components.LoadingAnimation
import ui.components.NoDeviceFound
import ui.components.Tooltip
import ui.dialogs.DialogConfirmAppUninstall

@OptIn(DelicateCoroutinesApi::class)
@Composable
fun AppsView() {
    var dropdownExpanded by remember { mutableStateOf(false) }
    var deviceList: List<MobileDevice> by remember { mutableStateOf(listOf()) }
    var deviceAppList: List<MobileApplication> by remember { mutableStateOf(listOf()) }

    var selectedDevice: Int by remember { mutableStateOf(0) }

    LaunchedEffect(Dispatchers.IO) {
        while (true) {
            deviceList = MobileDeviceRepository.deviceList

            delay(50)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        if (deviceList.isEmpty()) {
            NoDeviceFound()
        }

        AnimatedVisibility(deviceList.isNotEmpty()) {
            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
            ) {
                Box {
                    OutlinedButton(
                        onClick = {
                            dropdownExpanded = true
                        }
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            deviceList.getOrNull(selectedDevice)?.getName()?.let {
                                Text(
                                    text = it,
                                    fontSize = 16.sp,
                                    color = MaterialTheme.colorScheme.onBackground
                                )
                            }

                            Icon(
                                painterResource("icons/expand_more.svg"),
                                null,
                                tint = MaterialTheme.colorScheme.onBackground
                            )
                        }
                    }

                    DropdownMenu(
                        expanded = dropdownExpanded,
                        onDismissRequest = { dropdownExpanded = false }
                    ) {
                        deviceList.forEach {
                            DropdownMenuItem(
                                text = { Text(it.getName()) },
                                onClick = {
                                    dropdownExpanded = false
                                    selectedDevice = deviceList.indexOf(it)

                                    deviceAppList = listOf()
                                    deviceAppList = deviceList[selectedDevice].getApplications().toList()
                                },
                                leadingIcon = {
                                    when (it.deviceType) {
                                        DeviceType.ANDROID -> Icon(
                                            painterResource("icons/adb.svg"),
                                            contentDescription = null
                                        )

                                        DeviceType.IOS -> Icon(
                                            painterResource("icons/apple.svg"),
                                            contentDescription = null,
                                            modifier = Modifier.height(24.dp)
                                        )
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }

        if (deviceList.isNotEmpty() && deviceList.getOrNull(selectedDevice) != null) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                GlobalScope.launch(Dispatchers.IO) {
                    while (true) {
                        deviceList.getOrNull(selectedDevice)?.let {
                            deviceAppList = it.getApplications().toList()
                        }

                        delay(1000)
                    }
                }

                if (deviceAppList.isNotEmpty()) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(bottom = 12.dp),

                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(deviceAppList) {
                            if (deviceList.isNotEmpty()) {
                                AppListItem(it, deviceList[selectedDevice])
                            }
                        }
                    }
                }
                else {
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),

                        contentAlignment = Alignment.Center
                    ) {
                        LoadingAnimation()
                    }
                }
            }
        }


    }
}

@OptIn(DelicateCoroutinesApi::class)
@Composable
fun AppListItem(application: MobileApplication, mobileDevice: MobileDevice) {
    var showUninstallDialog: Boolean by remember { mutableStateOf(false) }
    var showApplicationItem: Boolean by remember { mutableStateOf(true) }

    AnimatedVisibility(showApplicationItem) {
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
                Icon(
                    painter = painterResource("icons/image_not_supported.svg"),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.background
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = application.id,
                fontWeight = FontWeight.Light,
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.weight(1F))

            Row {
                Tooltip(tip = "Open this App") {
                    IconButton(
                        onClick = {
                            GlobalScope.launch(Dispatchers.IO) {
                                mobileDevice.launchApplication(application)
                            }
                        },
                        content = {
                            Icon(
                                painter = painterResource("icons/play_arrow.svg"),
                                contentDescription = "Open App",
                                tint = MaterialTheme.colorScheme.onBackground
                            )
                        }
                    )
                }

                if (mobileDevice.deviceType != DeviceType.IOS && mobileDevice.isEmulator.not()) {
                    Tooltip(tip = "Clear all Data") {
                        IconButton(
                            onClick = {
                                GlobalScope.launch(Dispatchers.IO) {
                                    mobileDevice.clearApplicationData(application)
                                }
                            },
                            content = {
                                Icon(
                                    painter = painterResource("icons/folder_off.svg"),
                                    contentDescription = "Clear Cache",
                                    tint = MaterialTheme.colorScheme.onBackground
                                )
                            }
                        )
                    }
                }

                if ((mobileDevice.deviceType == DeviceType.IOS && application.id.contains("com.apple.")).not()) {
                    Tooltip(tip = "Uninstall this App") {
                        IconButton(
                            onClick = {
                                showUninstallDialog = true
                            },
                            content = {
                                Icon(
                                    painter = painterResource("icons/delete.svg"),
                                    contentDescription = "Delete App",
                                    tint = MaterialTheme.colorScheme.onBackground
                                )
                            }
                        )
                    }
                }
            }
        }
    }

    if (showUninstallDialog) {
        DialogConfirmAppUninstall(
            application = application,
            onClose = {
                showUninstallDialog = false
            },
            onConfirm = {
                showUninstallDialog = false
                showApplicationItem = false

                GlobalScope.launch(Dispatchers.IO) {
                    mobileDevice.uninstallApplication(application)
                }
            }
        )
    }
}