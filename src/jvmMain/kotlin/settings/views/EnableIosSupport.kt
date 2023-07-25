package settings.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import settings.GlobalSettings
import settings.SettingsViewModel

class EnableIosSupport : SettingsViewModel {
    @Composable
    override fun content() {
        var checked by remember { mutableStateOf(GlobalSettings.iosSupportEnabled) }

        Row(
            modifier = Modifier
                .fillMaxSize()
                .height(50.dp),

            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Support iOS Devices",
                fontSize = 18.sp
            )

            Switch(
                checked = checked,
                onCheckedChange = {
                    checked = it

                    GlobalSettings.iosSupportEnabled = checked
                }
            )
        }
    }
}