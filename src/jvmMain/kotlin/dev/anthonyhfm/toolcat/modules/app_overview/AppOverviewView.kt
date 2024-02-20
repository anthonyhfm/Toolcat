package dev.anthonyhfm.toolcat.modules.app_overview

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.anthonyhfm.toolcat.modules.app_overview.views.AppPreview

@OptIn(ExperimentalLayoutApi::class)
@Composable
internal fun AppOverviewView() {
    FlowRow(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp, vertical = 16.dp),

        horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterHorizontally),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        for (i in 0 .. 16) {
            AppPreview()
        }
    }
}