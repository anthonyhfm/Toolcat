package dev.anthonyhfm.toolcat.modules.app_overview.views

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.anthonyhfm.toolcat.main.theme.Inter

@Composable
internal fun AppOverviewNoDevices() {
    Box(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),

            verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource("icons/mobile_off.svg"),
                contentDescription = "No device found",
                modifier = Modifier
                    .size(96.dp),
                tint = MaterialTheme.colorScheme.error
            )

            Spacer(Modifier)

            Text(
                text = "No devices found.",
                fontSize = 24.sp,
                fontFamily = Inter,
                color = MaterialTheme.colorScheme.onBackground.copy(0.8f)
            )
        }
    }
}