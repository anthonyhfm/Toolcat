package quickactions.actions

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import jdk.jshell.spi.ExecutionControl.NotImplementedException
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import mobile.MobileDevice
import mobile.firmware.disableWifi
import mobile.firmware.enableWifi
import mobile.firmware.getWiFiActivated
import quickactions.QuickAction
import quickactions.QuickActionAvailability
import quickactions.QuickActionSize

class ToggleWifiAction : QuickAction {
    override val actionSize = QuickActionSize.SMALL
    override val availability = QuickActionAvailability.ANDROID

    @OptIn(DelicateCoroutinesApi::class)
    @Composable
    override fun content(mobileDevice: MobileDevice) {
        var wifiEnabled: Boolean by remember { mutableStateOf(true) }

        GlobalScope.launch {
            wifiEnabled = mobileDevice.getWiFiActivated()
        }

        Column(
            modifier = Modifier
                .fillMaxSize(1f),

            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(
                        when (wifiEnabled) {
                            true -> Color(18, 109, 255)
                            false -> Color.Gray.copy(alpha = 0.4F)
                        }
                    )
                    .aspectRatio(1F)
                    .weight(1F)
                    .clickable {
                        if (wifiEnabled) {
                            GlobalScope.launch { mobileDevice.disableWifi() }
                        } else {
                            GlobalScope.launch { mobileDevice.enableWifi() }
                        }

                        wifiEnabled = !wifiEnabled
                    },

                contentAlignment = Alignment.Center
            ) {
                when (wifiEnabled) {
                    true -> Icon(
                        painter = painterResource("icons/actions/wifi.svg"),
                        contentDescription = "Wi-Fi is enabled",
                        tint = Color.White,
                        modifier = Modifier.size(28.dp)
                    )

                    false -> Icon(
                        painter = painterResource("icons/actions/wifi_off.svg"),
                        contentDescription = "Wi-Fi is disabled",
                        modifier = Modifier.size(28.dp)
                    )
                }
            }

            Text(
                text = if (wifiEnabled) {
                    "Disable Wifi"
                } else {
                    "Enable Wifi"
                },
                maxLines = 1,
                fontSize = 14.sp
            )
        }
    }
}