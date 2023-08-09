package quickactions.actions

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import mobile.MobileDevice
import mobile.firmware.*
import quickactions.QuickAction
import quickactions.QuickActionAvailability
import quickactions.QuickActionSize
import ui.dialogs.BaseDialog

class ShutdownAction : QuickAction {
    override val actionSize = QuickActionSize.SMALL
    override val availability = listOf(
        QuickActionAvailability.ANDROID,
        QuickActionAvailability.ANDROID_SIM,
        QuickActionAvailability.IOS_SIM,
    )

    @OptIn(DelicateCoroutinesApi::class)
    @Composable
    override fun content(mobileDevice: MobileDevice) {
        var showDialog: Boolean by remember { mutableStateOf(false) }

        Column(
            modifier = Modifier
                .fillMaxSize(1f),

            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(Color.Red.copy(alpha = 0.8f))
                    .aspectRatio(1F)
                    .weight(1F)
                    .clickable {
                        showDialog = true
                    },

                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource("icons/actions/power_settings_new.svg"),
                    contentDescription = "Shutdown Icon",
                    tint = Color.White,
                    modifier = Modifier.size(28.dp)
                )
            }

            Text(
                text = "Shutdown",
                maxLines = 1,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onBackground
            )
        }

        if (showDialog) {
            DialogConfirmShutdown(
                onClose = {
                    showDialog = false
                },
                onConfirm = {
                    GlobalScope.launch {
                        mobileDevice.deviceShutdown()
                    }
                }
            )
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    @Composable
    private fun DialogConfirmShutdown(
        onClose: () -> Unit,
        onConfirm: () -> Unit
    ) {
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
                                .width(300.dp)
                        ) {
                            Text(
                                text = "Shutdown",
                                fontSize = 22.sp,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .fillMaxWidth(),
                                color = MaterialTheme.colorScheme.onBackground
                            )
                            Spacer(Modifier.height(12.dp))

                            Text(
                                text = "Are you sure that you want to shutdown your device?",
                                textAlign = TextAlign.Center,
                                color = MaterialTheme.colorScheme.onBackground
                            )

                            Row(
                                horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.End)
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

                                Button(
                                    onClick = {
                                        isDialogVisible = false

                                        GlobalScope.launch {
                                            delay(250)
                                            onConfirm()
                                        }
                                    }
                                ) {
                                    Text("Shutdown")
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
}