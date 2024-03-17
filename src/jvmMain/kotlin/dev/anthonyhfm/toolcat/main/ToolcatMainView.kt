package dev.anthonyhfm.toolcat.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import dev.anthonyhfm.toolcat.core.module.ModuleRegistry
import dev.anthonyhfm.toolcat.core.module.ToolcatModule
import dev.anthonyhfm.toolcat.main.theme.Inter
import dev.anthonyhfm.toolcat.modules.app_overview.AppOverviewModule
import dev.anthonyhfm.toolcat.modules.device_overview.DeviceOverviewModule
import dev.anthonyhfm.toolcat.modules.more_modules.MoreModulesModule
import dev.anthonyhfm.toolcat.modules.screen_cast.ScreenCastModule
import dev.anthonyhfm.toolcat.modules.toolcat_about.AboutModule
import dev.anthonyhfm.toolcat.modules.toolcat_settings.SettingsModule
import dev.anthonyhfm.toolcat.navigation.Navigation
import dev.anthonyhfm.toolcat.navigation.Sidebar

@Composable
fun ToolcatMainView() {
    var module: ToolcatModule? by remember { mutableStateOf(null) }

    LaunchedEffect(Navigation.currentModule.value) {
        module = ModuleRegistry.getModuleById(Navigation.currentModule.value)
    }

    Row {
        Sidebar.View()

        Box(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .fillMaxSize()
        ) {
            if (module != null) {
                module!!.ModuleView()
            }
        }
    }
}
