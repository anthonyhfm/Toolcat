package settings.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import settings.GlobalSettings
import settings.SettingsViewModel
import utils.Device
import utils.OperatingSystem

class IOSSimulatorSupportSettingsModel : SettingsViewModel {
    override val osSupport: List<OperatingSystem> = listOf(
        OperatingSystem.MACOS,
    )

    @Composable
    override fun content() {
        var checked by remember { mutableStateOf(GlobalSettings.iosSimulatorSupportEnabled) }
        val supported: Boolean = osSupport.contains(Device.getOS())

        Row(
            modifier = Modifier
                .fillMaxSize()
                .height(40.dp)
                .alpha(if (supported) 1F else 0.4f),

            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "Advanced iOS Simulator Support",
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )

                Text(
                    text = if (supported) "Experimental" else "Not Supported",
                    fontWeight = FontWeight.Thin,
                    fontStyle = FontStyle.Italic,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
                )
            }

            Switch(
                checked = checked,
                onCheckedChange = {
                    if (supported) {
                        checked = it

                        GlobalSettings.iosSimulatorSupportEnabled = checked
                        GlobalSettings.saveGlobalSettings()
                    }
                }
            )
        }
    }
}