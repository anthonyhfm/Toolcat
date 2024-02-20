package dev.anthonyhfm.toolcat.modules.app_overview.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun AppPreview() {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(4.dp))
            .width(150.dp)
            .height(164.dp)
            .background(MaterialTheme.colorScheme.primary.copy(0.1f))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),

                verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterVertically),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(64.dp)
                        .background(MaterialTheme.colorScheme.primary)
                )

                Text(
                    text = "App"
                )
            }

            Row(
                modifier = Modifier
                    .height(48.dp)
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primary.copy(0.1f)),

                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(
                    onClick = {

                    }
                ) {
                    Icon(Icons.Default.ExitToApp, null)
                }

                IconButton(
                    onClick = {

                    }
                ) {
                    Icon(Icons.Default.ExitToApp, null)
                }

                IconButton(
                    onClick = {

                    }
                ) {
                    Icon(Icons.Default.ExitToApp, null)
                }
            }
        }
    }
}