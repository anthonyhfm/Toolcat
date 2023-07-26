package ui.views

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import mobile.*

@OptIn(DelicateCoroutinesApi::class)
@Composable
fun AppsView() {
    var dropdownExpanded by remember { mutableStateOf(false) }
    var deviceList: List<MobileDevice> by remember {
        mutableStateOf(
            listOf()
        )
    }

    var selectedDevice: Int by remember { mutableStateOf(0) }

    GlobalScope.launch {
        MobileDeviceRepository.fetchConnectedDevices()

        deviceList = MobileDeviceRepository.deviceList
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
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
                            Text(
                                text = deviceList[selectedDevice].getName(),
                                fontSize = 16.sp,
                                color = Color.Black
                            )

                            Icon(
                                painterResource("icons/expand_more.svg"),
                                null,
                                tint = Color.Black
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

        if (deviceList.isNotEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                GlobalScope.launch {
                    deviceList[selectedDevice].getApplications()
                }
            }
        }
    }
}