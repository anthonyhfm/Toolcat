package settings.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.material3.Switch
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import settings.GlobalSettings
import settings.SettingsViewModel

class IOSSimulatorSupportSettingsModel : SettingsViewModel {
    @Composable
    override fun content() {
        var checked by remember { mutableStateOf(GlobalSettings.iosSimulatorSupportEnabled) }

        Row(
            modifier = Modifier
                .fillMaxSize()
                .height(40.dp),

            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "Advanced iOS Simulator Support",
                    fontSize = 18.sp
                )

                Text(
                    text = "Experimental",
                    fontWeight = FontWeight.Thin,
                    fontStyle = FontStyle.Italic
                )
            }

            Switch(
                checked = checked,
                onCheckedChange = {
                    checked = it

                    GlobalSettings.iosSupportEnabled = checked
                    GlobalSettings.saveGlobalSettings()
                }
            )
        }
    }
}