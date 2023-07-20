package ui.views

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import mobile.MobileDevice
import mobile.MobileDeviceRepository
import ui.components.DeviceActionsSidebar
import ui.components.DeviceListSidebar

data class NavigationItem(
    val name: String,
    val icon: @Composable () -> Unit,
    val view: @Composable () -> Unit,
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainView() {
    val deviceList: List<MobileDevice> = MobileDeviceRepository().getConnectedDevices()
    // var selectedMobileDevice: MobileDevice by remember { mutableStateOf(deviceList[0]) }

    var selectedView by remember { mutableStateOf(0) }

    val navigationItems: List<NavigationItem> = listOf(
        NavigationItem(
            name = "Devices",
            icon = { Icon(painterResource("icons/smartphone.svg"), "") },
            view = { DevicesView() }
        ),
        NavigationItem(
            name = "Apps",
            icon = { Icon(painterResource("icons/apps.svg"), "") },
            view = { DevicesView() }
        ),
        NavigationItem(
            name = "Cast",
            icon = { Icon(painterResource("icons/cast.svg"), "") },
            view = { CastView() }
        ),
        NavigationItem(
            name = "Settings",
            icon = { Icon(Icons.Default.Settings, "") },
            view = { SettingsView() }
        ),
        NavigationItem(
            name = "About",
            icon = { Icon(Icons.Default.Info, "") },
            view = { AboutView() }
        )
    )

    Row {
        NavigationRail {
            navigationItems.forEachIndexed { index, item ->
                if (index == navigationItems.count() -1) {
                    Spacer(modifier = Modifier.weight(1F))
                }

                NavigationRailItem(
                    icon = { item.icon() },
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
            navigationItems[selectedView].view()
        }
    }
}