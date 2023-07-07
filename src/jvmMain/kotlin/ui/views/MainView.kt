package ui.views

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ui.components.DeviceListSidebar

@Composable
fun MainView() {
    Row(
        modifier = Modifier
            .fillMaxSize()
    ) {
        DeviceListSidebar {

        }
    }
}