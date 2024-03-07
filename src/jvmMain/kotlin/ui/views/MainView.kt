package ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource

data class NavigationItem(
    val name: String,
    val icon: @Composable () -> Unit,
    val view: @Composable () -> Unit,
)

@Composable
fun MainView() {
    var selectedView by remember { mutableStateOf(0) }

    val navigationItems: List<NavigationItem> = listOf(
        NavigationItem(
            name = "Cast",
            icon = { Icon(painterResource("icons/cast.svg"), "") },
            view = { CastView() }
        ),
    )

    Row {
        NavigationRail {
            navigationItems.forEachIndexed { index, item ->
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
