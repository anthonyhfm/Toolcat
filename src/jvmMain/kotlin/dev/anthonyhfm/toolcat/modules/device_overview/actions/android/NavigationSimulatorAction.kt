package dev.anthonyhfm.toolcat.modules.device_overview.actions.android

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
import dev.anthonyhfm.toolcat.core.platform.android.system.navigateBack
import dev.anthonyhfm.toolcat.core.platform.android.system.navigateHome
import dev.anthonyhfm.toolcat.modules.device_overview.actions.QuickActionModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NavigationSimulatorAction(override val device: AndroidDevice) : QuickActionModel<AndroidDevice>(device) {
    private data class NavigationButton(
        val name: String,
        val icon: String,
        val onClick: () -> Unit
    )

    private val navigationButtons: List<NavigationButton> = listOf(
        NavigationButton(
            name = "Back",
            icon = "actions/android_back.svg",
            onClick = {
                device.navigateBack()
            }
        ),
        NavigationButton(
            name = "Home",
            icon = "actions/android_home.svg",
            onClick = {
                device.navigateHome()
            }
        ),
    )

    @Composable
    override fun Content() {
        val scope = rememberCoroutineScope()

        Column(
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(vertical = 8.dp),

            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                navigationButtons.forEach {
                    Box(
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(Color.Gray)
                            .size(64.dp)
                            .clickable {
                                scope.launch(Dispatchers.IO) {
                                    it.onClick()
                                }
                            },

                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource("icons/${it.icon}"),
                            contentDescription = it.name,
                            tint = Color.White,
                            modifier = Modifier.size(28.dp)
                        )
                    }
                }
            }

            Text(
                text = "Simulate Navigation",
                maxLines = 1,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}
