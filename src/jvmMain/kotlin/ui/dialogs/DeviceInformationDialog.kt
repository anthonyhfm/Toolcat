package ui.dialogs

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Divider
import androidx.compose.material3.TextButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.*
import mobile.MobileDevice
import mobile.hardware.getBatteryLevel
import kotlin.time.Duration.Companion.seconds

data class DeviceInformationData(
    val title: String,
    val fetch: (MobileDevice) -> String,
    var value: String? = null
)

@Composable
fun DeviceInformationSlot(title: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 6.dp),

        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onBackground
        )

        Text(
            text = value,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

@OptIn(DelicateCoroutinesApi::class)
@Composable
fun DeviceInformationDialog(mobileDevice: MobileDevice, onClose: () -> Unit) {
    var isDialogVisible by remember { mutableStateOf(false ) }

    BaseDialog {
        AnimatedVisibility(
            visible = isDialogVisible,
            enter = fadeIn(
                initialAlpha = 0f
            ),
            exit = fadeOut(
                animationSpec = tween(durationMillis = 250),
                targetAlpha = 0f
            )
        ) {
            Box(
                modifier = Modifier
                    .background(Color.Black.copy(alpha = 0.6f))
                    .fillMaxSize(),

                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(24.dp))
                        .background(MaterialTheme.colorScheme.background)
                        .padding(24.dp)
                ) {
                    Column(
                        horizontalAlignment = Alignment.End,
                        verticalArrangement = Arrangement.spacedBy(12.dp),

                        modifier = Modifier
                            .width(360.dp)
                    ) {
                        Text(
                            text = "Device Information",
                            fontSize = 22.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth(),
                            color = MaterialTheme.colorScheme.onBackground
                        )
                        Spacer(Modifier.height(12.dp))

                        LazyColumn {
                            items(deviceInformationList) {
                                var valueState by remember { mutableStateOf<String?>(null) }

                                DisposableEffect(Unit) {
                                    val job = GlobalScope.launch(Dispatchers.Default) {
                                        val value = it.fetch(mobileDevice)
                                        valueState = value
                                    }
                                    onDispose { job.cancel() }
                                }

                                val value = valueState
                                if (value != null) {
                                    DeviceInformationSlot(title = it.title, value = value)

                                    if (deviceInformationList.indexOf(it) != deviceInformationList.count() - 1) {
                                        Divider(modifier = Modifier.padding(8.dp))
                                    }
                                }
                            }
                        }

                        Row(
                            horizontalArrangement = Arrangement.End
                        ) {
                            TextButton(
                                onClick = {
                                    isDialogVisible = false

                                    GlobalScope.launch {
                                        delay(250)
                                        onClose()
                                    }
                                }
                            ) {
                                Text("Close")
                            }
                        }
                    }
                }
            }
        }

        LaunchedEffect(Unit) {
            isDialogVisible = true
        }
    }
}