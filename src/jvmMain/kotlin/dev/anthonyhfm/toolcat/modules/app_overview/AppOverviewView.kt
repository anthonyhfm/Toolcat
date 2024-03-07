package dev.anthonyhfm.toolcat.modules.app_overview

import androidx.compose.foundation.VerticalScrollbar
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.rememberScrollbarAdapter
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.anthonyhfm.toolcat.core.platform.android.AndroidDeviceRepository
import dev.anthonyhfm.toolcat.core.platform.android.system.getAppPackages
import dev.anthonyhfm.toolcat.core.utils.GlobalSettings
import dev.anthonyhfm.toolcat.main.views.VerticalScrollColumn
import dev.anthonyhfm.toolcat.modules.app_overview.views.AppList
import dev.anthonyhfm.toolcat.modules.app_overview.views.AppOverviewHeader
import dev.anthonyhfm.toolcat.modules.app_overview.views.AppPreview
import dev.anthonyhfm.toolcat.modules.app_overview.views.LegacyAppList

@Composable
internal fun AppOverviewView() {
    Column {
        AppOverviewHeader()

        Divider(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth(0.9f)
        )

        VerticalScrollColumn {
            if (GlobalSettings.useLegacyAppList.value) {
                LegacyAppList()
            } else {
                AppList()
            }
        }
    }
}