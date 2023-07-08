package ui.components

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import mobile.MobileDevice
import mobile.actions.MobileAction
import mobile.actions.OpenScreenShareMobileAction

val mobileActions: List<MobileAction> = listOf(
    OpenScreenShareMobileAction()
)

@Composable
fun DeviceActionsSidebar(mobileDevice: MobileDevice) {
    LazyColumn(
        modifier = Modifier
            .fillMaxHeight()
            .width(220.dp)
            .padding(vertical = 16.dp),

        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(mobileActions) { action: MobileAction ->  
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    action.executeAction(mobileDevice)
                }
            ) {
                Text(action.displayText)
            }
        }
    }
}