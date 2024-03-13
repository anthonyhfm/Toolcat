package dev.anthonyhfm.toolcat.modules.app_overview.views

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.anthonyhfm.toolcat.core.platform.android.AndroidDevice
import dev.anthonyhfm.toolcat.core.platform.android.system.getAppPackages
import dev.anthonyhfm.toolcat.main.views.VerticalScrollColumn
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
internal fun LegacyAppList(device: AndroidDevice) {
    val scope = rememberCoroutineScope()
    var deviceAppList: List<String> by remember { mutableStateOf(listOf()) }

    LaunchedEffect(Unit) {
        scope.launch(Dispatchers.IO) {
            deviceAppList = device.getAppPackages()
        }
    }

    if (deviceAppList.isEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxSize(),

            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(Modifier.size(50.dp))
        }
    }

    AnimatedVisibility(
        visible = deviceAppList.isNotEmpty(),
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        VerticalScrollColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp, vertical = 16.dp),

                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                deviceAppList.forEach {
                    LegacyAppPreview(device, it) {
                        scope.launch(Dispatchers.IO) {
                            deviceAppList = device.getAppPackages()
                        }
                    }
                }
            }
        }
    }
}