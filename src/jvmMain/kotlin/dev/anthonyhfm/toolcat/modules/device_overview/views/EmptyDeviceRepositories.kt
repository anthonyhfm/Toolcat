package dev.anthonyhfm.toolcat.modules.device_overview.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.anthonyhfm.toolcat.main.theme.Inter

@Composable
internal fun EmptyDeviceRepositories() {
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

        FakeListBackground()
    }
}

@Composable
private fun FakeListBackground() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .alpha(0.6f),

        verticalArrangement = Arrangement.spacedBy(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier)

        DeviceCollectionView("Devices", enabled = false) {
            for (i in 1..5) {
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .alpha(1f - 0.15f * i)
                        .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1F))
                        .fillMaxWidth()
                        .height(60.dp)
                        .padding(10.dp),

                )
            }
        }

        Spacer(Modifier)
    }
}