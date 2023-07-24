package ui.views

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import mobile.MobileDevice
import mobile.MobileDeviceRepository
import mobile.getManufacturerName
import mobile.getProductName

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CastView() {
    var dropdownExpanded by remember { mutableStateOf(false) }
    val deviceList: List<MobileDevice> by remember {
        mutableStateOf(
            MobileDeviceRepository().getConnectedDevices()
        )
    }

    var selectedDevice: Int by remember { mutableStateOf(0) }

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
                .size(96.dp)
        )

        Spacer(modifier = Modifier.height(18.dp))

        Text(
            text = "Device Screen Mirroring",
            fontWeight = FontWeight.SemiBold,
            fontSize = 24.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            Modifier.fillMaxWidth(0.75F),

            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Select a device to mirror its screen.",
                textAlign = TextAlign.Center
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
                            text = deviceList[selectedDevice].getManufacturerName() + " " + deviceList[selectedDevice].getProductName(),
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

                FilledIconButton(
                    onClick = {
                        Runtime.getRuntime().exec("scrcpy --serial ${deviceList[selectedDevice].serial} --window-title=\"Toolcat Screen Mirror (${deviceList[selectedDevice].getProductName()})\"")
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
                        text = { Text(it.getManufacturerName() + " " + it.getProductName()) },
                        onClick = {
                            dropdownExpanded = false
                        },
                        leadingIcon = {
                            Icon(
                                painterResource("icons/adb.svg"),
                                contentDescription = null
                            )
                        }
                    )
                }
            }
        }
    }
}