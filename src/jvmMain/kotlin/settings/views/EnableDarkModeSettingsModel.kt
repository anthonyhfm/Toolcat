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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import settings.GlobalSettings
import settings.SettingsViewModel
import ui.theme.ToolcatTheme
import utils.OperatingSystem

class EnableDarkModeSettingsModel : SettingsViewModel {
    @Composable
    override fun content() {
        val checked by remember { mutableStateOf(GlobalSettings.enableDarkMode) }

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
                    text = "Enable Dark Theme",
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }

            Switch(
                checked = checked.value,
                onCheckedChange = {
                    checked.value = it

                    GlobalSettings.enableDarkMode = checked
                    GlobalSettings.saveGlobalSettings()
                }
            )
        }
    }
}
