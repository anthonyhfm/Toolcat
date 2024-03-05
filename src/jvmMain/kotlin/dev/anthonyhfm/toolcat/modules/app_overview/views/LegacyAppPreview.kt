package dev.anthonyhfm.toolcat.modules.app_overview.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.anthonyhfm.toolcat.main.theme.Inter

@Composable
internal fun LegacyAppPreview() {
    Row(
        modifier = Modifier
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1F))
            .fillMaxWidth()
            .height(60.dp)
            .padding(10.dp),

        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .fillMaxHeight()
                .background(MaterialTheme.colorScheme.primary)
                .padding(horizontal = 16.dp),

            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "de.anthonyhfm.toolcat",
                fontFamily = Inter,
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = 18.sp,
                lineHeight = 18.sp
            )
        }

        Row(
            modifier = Modifier
                .fillMaxHeight(),

            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            IconButton(
                onClick = {

                }
            ) {
                Icon(painterResource("icons/play_arrow.svg"), null, tint = MaterialTheme.colorScheme.onBackground)
            }

            IconButton(
                onClick = {

                }
            ) {
                Icon(Icons.Outlined.Info, null, tint = MaterialTheme.colorScheme.onBackground)
            }

            IconButton(
                onClick = {

                }
            ) {
                Icon(Icons.Default.MoreVert, null, tint = MaterialTheme.colorScheme.onBackground)
            }
        }
    }
}
