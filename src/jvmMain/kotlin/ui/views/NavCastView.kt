package ui.views

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.*
import mobile.*

@Composable
fun CastView() {
    var dropdownExpanded by remember { mutableStateOf(false) }
    var deviceList: List<MobileDevice> by remember { mutableStateOf(listOf()) }

    LaunchedEffect(Dispatchers.IO) {
        while (true) {
            deviceList = MobileDeviceRepository.deviceList.filter { it.deviceType == DeviceType.ANDROID }
            delay(10)
        }
    }

    var selectedDevice: Int by remember { mutableStateOf(0) }

    if (deviceList.isEmpty()) {
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize(),

            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource("icons/cast.svg"),
                contentDescription = "Screen Cast Icon",
                modifier = Modifier
                    .size(96.dp),
                tint = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Device Screen Mirroring",
                fontWeight = FontWeight.SemiBold,
                fontSize = 24.sp,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                Modifier.fillMaxWidth(0.75F),

                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Select a device to mirror its screen.",
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            AnimatedVisibility(deviceList.isNotEmpty()) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
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
                                color = MaterialTheme.colorScheme.onBackground
                            )

                            Icon(
                                painterResource("icons/expand_more.svg"),
                                null,
                                tint = MaterialTheme.colorScheme.onBackground
                            )
                        }
                    }

                    FilledIconButton(
                        onClick = {
                            deviceList[selectedDevice].openScreenMirror()
                        }
                    ) {
                        Icon(
                            painterResource("icons/cast.svg"),
                            null
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
}