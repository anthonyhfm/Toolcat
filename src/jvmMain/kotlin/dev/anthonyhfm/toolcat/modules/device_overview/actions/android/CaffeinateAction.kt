package dev.anthonyhfm.toolcat.modules.device_overview.actions.android

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.anthonyhfm.toolcat.core.platform.android.AndroidDevice
import dev.anthonyhfm.toolcat.core.platform.android.system.forceReboot
import dev.anthonyhfm.toolcat.modules.device_overview.actions.QuickActionModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CaffeinateAction(override val device: AndroidDevice) : QuickActionModel<AndroidDevice>(device) {
    @Composable
    override fun Content() {
        val scope = rememberCoroutineScope()
        var caffeinated: Boolean by remember {
            mutableStateOf(
                when (
                    device
                        .adb
                        .shell("settings get global stay_on_while_plugged_in")
                        .allOutput
                        .trimIndent()) {
                    "0" -> false
                    else -> true
                }
            )
        }

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
                    .background(
                        animateColorAsState(
                            targetValue = if (caffeinated) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.tertiary.copy(0.6f)
                        ).value
                    )
                    .size(64.dp)
                    .clickable {
                        scope.launch(Dispatchers.IO) {
                            device.adb.shell("svc power stayon ${!caffeinated}")

                            caffeinated = !caffeinated
                        }
                    },

                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource("icons/actions/coffee.svg"),
                    contentDescription = "Caffeinate",
                    tint = Color.White,
                    modifier = Modifier.size(28.dp)
                )
            }

            Text(
                text = when (caffeinated) {
                    false -> "Caffeinate"
                    true -> "De-caffeinate"
                },
                maxLines = 1,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}
