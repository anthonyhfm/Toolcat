package dev.anthonyhfm.toolcat.modules.app_overview.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
internal fun LegacyAppList() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 16.dp),

        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        for (i in 0..16) {
            LegacyAppPreview()
        }
    }
}