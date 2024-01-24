package dev.anthonyhfm.toolcat.modules.toolcat_settings

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.anthonyhfm.toolcat.main.theme.Inter
import dev.anthonyhfm.toolcat.main.views.VerticalScrollColumn

@Composable
internal fun ApplicationSettingsView(vm: SettingsModuleViewModel) {
    VerticalScrollColumn(
        modifier = Modifier
            .fillMaxSize(),

        verticalArrangement = Arrangement.spacedBy(48.dp)
    ) {
        Spacer(Modifier)

        vm.getCategories().forEach {
            Column(
                modifier = Modifier
                    .padding(horizontal = 48.dp)
            ) {
                Text(
                    text = it.name,
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
                    it.settings.forEach {
                        it.Setting()
                    }
                }
            }
        }

        Spacer(Modifier)
    }
}
