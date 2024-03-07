package dev.anthonyhfm.toolcat.modules.toolcat_settings.presets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.anthonyhfm.toolcat.main.theme.Inter

class ToggleSetting(
    val displayText: String,
    val state: MutableState<Boolean>,
    val onValueChange: (Boolean) -> Unit
) : SettingModel {
    @Composable
    override fun Setting() {
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
                    text = displayText,
                    fontSize = 18.sp,
                    fontFamily = Inter,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }

            Switch(
                checked = state.value,
                onCheckedChange = {
                    onValueChange(it)
                }
            )
        }
    }
}
