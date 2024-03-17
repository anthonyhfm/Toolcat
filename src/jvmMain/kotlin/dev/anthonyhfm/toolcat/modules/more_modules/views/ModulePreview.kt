package dev.anthonyhfm.toolcat.modules.more_modules.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import dev.anthonyhfm.toolcat.core.module.ModuleRegistry
import dev.anthonyhfm.toolcat.core.module.ToolcatModule
import dev.anthonyhfm.toolcat.main.theme.Inter
import dev.anthonyhfm.toolcat.navigation.Navigation
import dev.anthonyhfm.toolcat.navigation.Sidebar

@Composable
internal fun ModulePreview(moduleID: String) {
    val module: ToolcatModule by remember { mutableStateOf(ModuleRegistry.getModuleById(moduleID)) }
    var pinned: Boolean by remember { mutableStateOf(Sidebar.isPinned(moduleID)) }

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
                    .clickable {
                        Navigation.openPage(moduleID)
                    }
                    .weight(1f)
                    .fillMaxWidth(),

                verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(56.dp)
                        .background(MaterialTheme.colorScheme.primary),

                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(module.iconResource),
                        tint = MaterialTheme.colorScheme.onPrimary,
                        contentDescription = null,
                        modifier = Modifier.size(32.dp)
                    )
                }

                Text(
                    text = module.name,
                    fontFamily = Inter,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }

            Row(
                modifier = Modifier
                    .clickable {
                        if (pinned) {
                            Sidebar.unpin(moduleID)
                        } else {
                            Sidebar.pin(moduleID)
                        }

                        pinned = !pinned
                    }
                    .height(48.dp)
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.secondary),

                horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(if (pinned) "icons/keep_off.svg" else "icons/keep.svg"),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSecondary,
                )

                Text(
                    text = if (pinned) "Unpin" else "Pin",
                    fontFamily = Inter,
                    color = MaterialTheme.colorScheme.onSecondary
                )
            }
        }
    }
}