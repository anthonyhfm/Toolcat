package settings.views

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import settings.GlobalSettings
import settings.SettingsViewModel
import ui.components.ThemePreview
import ui.theme.ToolcatTheme

class ThemeSelectionSettingsModel : SettingsViewModel {
    @Composable
    override fun content() {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,

                modifier = Modifier
                    .height(40.dp)
            ) {
                Text(
                    text = "Select a Theme",
                    fontSize = 18.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }

            LazyRow(
                modifier = Modifier
                    .fillMaxWidth(),

                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(ToolcatTheme.themeCollection) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier
                                .border(
                                    BorderStroke(
                                        4.dp,
                                        if (GlobalSettings.selectedTheme.value == ToolcatTheme.themeCollection.indexOf(it)) {
                                            MaterialTheme.colorScheme.primary
                                        }
                                        else {
                                            MaterialTheme.colorScheme.onBackground.copy(alpha = 0.2F)
                                        }
                                    ),
                                    RoundedCornerShape(6.dp)
                                )
                                .padding(8.dp)
                                .clickable {
                                    GlobalSettings.selectedTheme.value = ToolcatTheme.themeCollection.indexOf(it)
                                    GlobalSettings.saveGlobalSettings()
                                }
                        ) {
                            ThemePreview(it)
                        }

                        Text(
                            text = it.name,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }
                }
            }
        }
    }
}
