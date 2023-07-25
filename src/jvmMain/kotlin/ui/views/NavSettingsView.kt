package ui.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import settings.settingsClusterList

@Composable
fun SettingsView() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 32.dp),

        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(settingsClusterList) {
            Row(
                modifier = Modifier.height(40.dp).fillMaxWidth(0.8F),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = it.title,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 28.sp
                )
            }

            Column(
                modifier = Modifier.fillMaxWidth(0.8F).padding(horizontal = 32.dp)
            ) {
                it.settings.forEach {
                    it.content()
                }
            }
        }
    }
}