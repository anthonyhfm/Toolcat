package mobile.quickactions.actions

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
import jdk.jshell.spi.ExecutionControl
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import mobile.MobileDevice
import mobile.firmware.*
import mobile.quickactions.QuickAction
import mobile.quickactions.QuickActionAvailability
import mobile.quickactions.QuickActionSize

class ToggleBluetoothAction : QuickAction {
    override val actionSize = QuickActionSize.SMALL
    override val availability = QuickActionAvailability.ANDROID

    override fun execute(mobileDevice: MobileDevice) {
        throw ExecutionControl.NotImplementedException("Not used in the ToggleWifiAction")
    }

    @OptIn(DelicateCoroutinesApi::class)
    @Composable
    override fun content(mobileDevice: MobileDevice) {
        var bluetoothEnabled: Boolean by remember { mutableStateOf(false) }

        GlobalScope.launch {
            bluetoothEnabled = mobileDevice.getBluetoothActivated()
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
                        when (bluetoothEnabled) {
                            true -> Color(18, 109, 255)
                            false -> Color.Gray.copy(alpha = 0.4F)
                        }
                    )
                    .aspectRatio(1F)
                    .weight(1F)
                    .clickable {
                        if (bluetoothEnabled) {
                            GlobalScope.launch { mobileDevice.disableBluetooth() }
                        } else {
                            GlobalScope.launch { mobileDevice.enableBluetooth() }
                        }

                        bluetoothEnabled = !bluetoothEnabled
                    },

                contentAlignment = Alignment.Center
            ) {
                when (bluetoothEnabled) {
                    true -> Icon(
                        painter = painterResource("icons/actions/bluetooth.svg"),
                        contentDescription = "Bluetooth is enabled",
                        tint = Color.White,
                        modifier = Modifier.size(28.dp)
                    )

                    false -> Icon(
                        painter = painterResource("icons/actions/bluetooth_disabled.svg"),
                        contentDescription = "Bluetooth is disabled",
                        modifier = Modifier.size(28.dp)
                    )
                }
            }

            Text(
                text = if (bluetoothEnabled) {
                    "Disable Bluetooth"
                } else {
                    "Enable Bluetooth"
                },
                maxLines = 1,
                fontSize = 14.sp
            )
        }
    }
}