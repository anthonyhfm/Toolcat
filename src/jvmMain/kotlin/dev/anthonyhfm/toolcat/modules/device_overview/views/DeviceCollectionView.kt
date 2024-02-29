package dev.anthonyhfm.toolcat.modules.device_overview.views

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import dev.anthonyhfm.toolcat.main.theme.Inter

@Composable
internal fun DeviceCollectionView(title: String, enabled: Boolean = true, content: @Composable () -> Unit) {
    var showContent: Boolean by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxWidth(0.9f),

        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Row(
            modifier = Modifier
                .height(32.dp)
                .fillMaxWidth(),

            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (enabled) {
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(32.dp)
                        .clickable {
                            showContent = !showContent
                        },

                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource("icons/chevron_right.svg"),
                        contentDescription = null,
                        modifier = Modifier
                            .rotate(
                                animateFloatAsState(
                                    targetValue = if (showContent) 90f else 0f
                                ).value
                            )
                            .size(24.dp),
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }
            }

            Text(
                text = title,
                fontFamily = Inter,
                color = MaterialTheme.colorScheme.onBackground
            )

            Divider(
                Modifier
                    .offset(y = 2.dp)
                    .background(MaterialTheme.colorScheme.onBackground.copy(0.1f))
            )
        }

        AnimatedVisibility(showContent) {
            Column(
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                content()
            }
        }
    }
}
