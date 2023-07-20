package ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import mobile.MobileDevice
import mobile.actions.InstallAPKFileMobileAction
import mobile.actions.MobileAction
import mobile.actions.OpenScreenShareMobileAction

val mobileActions: List<MobileAction> = listOf(
    InstallAPKFileMobileAction(),
    OpenScreenShareMobileAction()
)

@Composable
fun DeviceActionsSidebar(mobileDevice: MobileDevice) {
    LazyColumn(
        modifier = Modifier
            .fillMaxHeight()
            .width(220.dp)
            .padding(vertical = 16.dp),

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(mobileActions) { action: MobileAction ->  
            Row(
                modifier = Modifier
                    .clip(CircleShape)
                    .clickable {
                        action.executeAction(mobileDevice)
                    }
                    .background(Color(218, 222, 245))
                    .fillMaxWidth()
                    .height(40.dp),

                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(action.displayText)
            }
        }
    }
}