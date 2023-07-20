package ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import mobile.MobileDevice
import mobile.MobileDeviceRepository


@Composable
fun DeviceListSidebar(
    devices: List<MobileDevice>,
    onChange: (selectedDevice: Int) -> Unit,
) {
    var selectedDevice: Int by remember { mutableStateOf(0) }

    LazyColumn(
        modifier = Modifier
            .fillMaxHeight()
            .width(280.dp)
            .padding(16.dp),

        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(devices) { device ->
            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .clickable {
                        selectedDevice = devices.indexOf(device)
                        onChange(selectedDevice)
                    }
                    .background(Color(117, 129, 201))
                    .padding(10.dp)
                    .fillMaxWidth(),

                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(Color.White)
                        .size(36.dp),

                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painterResource("icons/adb.png"),
                        "Android Icon"
                    )
                }

                device.deviceName?.run {
                    Text(
                        text = this,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White
                    )
                }
            }
        }
    }
}