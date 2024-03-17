package dev.anthonyhfm.toolcat.modules.toolcat_settings

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.anthonyhfm.toolcat.core.utils.GlobalSettings
import dev.anthonyhfm.toolcat.main.theme.Inter
import dev.anthonyhfm.toolcat.main.views.VerticalScrollColumn
import dev.anthonyhfm.toolcat.modules.toolcat_settings.categories.AdvancedSettingsCategory
import dev.anthonyhfm.toolcat.modules.toolcat_settings.categories.SettingsCategory

@Composable
internal fun ApplicationSettingsView(vm: SettingsModule) {
    VerticalScrollColumn(
        modifier = Modifier
            .fillMaxSize(),

        verticalArrangement = Arrangement.spacedBy(48.dp)
    ) {
        Spacer(Modifier)

        vm.getCategories().forEach {
            SettingsCategoryView(it)
        }

        AnimatedVisibility(GlobalSettings.advancedSettings.value) {
            SettingsCategoryView(AdvancedSettingsCategory)
        }

        Spacer(Modifier)
    }
}

@Composable
private fun SettingsCategoryView(settings: SettingsCategory) {
    Column(
        modifier = Modifier
            .padding(horizontal = 48.dp)
    ) {
        Text(
            text = settings.name,
            fontSize = 32.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = Inter,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(Modifier.height(4.dp))
        Divider(color = MaterialTheme.colorScheme.onBackground.copy(0.1f))
        Spacer(Modifier.height(8.dp))

        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            settings.settings.forEach {
                it.Setting()
            }
        }
    }
}