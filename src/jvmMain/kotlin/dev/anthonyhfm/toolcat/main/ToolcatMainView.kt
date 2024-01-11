package dev.anthonyhfm.toolcat.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import dev.anthonyhfm.toolcat.core.module.ModuleViewModel
import dev.anthonyhfm.toolcat.modules.about.AboutModuleViewModel
import dev.anthonyhfm.toolcat.modules.settings.SettingsModuleViewModel

@Composable
fun ToolcatMainView() {
    var selectedView by remember { mutableStateOf(0) }

    val navigationItems: List<ModuleViewModel> = listOf(
        SettingsModuleViewModel,
        AboutModuleViewModel
    )

    Row {
        NavigationRail {
            navigationItems.forEachIndexed { index, item ->
                if (index == navigationItems.count() -1) {
                    Spacer(modifier = Modifier.weight(1F))
                }

                NavigationRailItem(
                    icon = { Icon(painterResource(item.iconResource), null) },
                    label = { Text(item.name) },
                    selected = selectedView == index,
                    onClick = { selectedView = index }
                )
            }
        }

        Box(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .fillMaxSize()
        ) {
            navigationItems[selectedView].ModuleView()
        }
    }
}
