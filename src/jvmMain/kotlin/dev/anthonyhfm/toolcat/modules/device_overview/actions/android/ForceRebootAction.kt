package dev.anthonyhfm.toolcat.modules.device_overview.actions.android

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toComposeImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.anthonyhfm.toolcat.core.platform.android.AndroidDevice
import dev.anthonyhfm.toolcat.core.platform.android.system.forceReboot
import dev.anthonyhfm.toolcat.modules.device_overview.actions.QuickActionModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ForceRebootAction(override val device: AndroidDevice) : QuickActionModel<AndroidDevice>(device) {
    @Composable
    override fun Content() {
        val scope = rememberCoroutineScope()

        Column(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .padding(vertical = 8.dp),

            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically)
        ) {
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.tertiary)
                    .size(64.dp)
                    .clickable {
                        scope.launch(Dispatchers.IO) {
                            device.forceReboot()
                        }
                    },

                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource("icons/actions/restart.svg"),
                    contentDescription = "Reboot Icon",
                    tint = Color.White,
                    modifier = Modifier.size(28.dp)
                )
            }

            Text(
                text = "Reboot",
                maxLines = 1,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}
