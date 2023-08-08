package quickactions.actions

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.*
import mobile.MobileDevice
import mobile.firmware.openDeepLink
import quickactions.QuickAction
import quickactions.QuickActionAvailability
import quickactions.QuickActionSize
import ui.dialogs.BaseDialog

class OpenDeepLinkAction : QuickAction {
    override val actionSize: QuickActionSize = QuickActionSize.SMALL
    override val availability = listOf(
        QuickActionAvailability.ANDROID,
        QuickActionAvailability.ANDROID_SIM,
        QuickActionAvailability.IOS_SIM,
    )

    @OptIn(DelicateCoroutinesApi::class)
    @Composable
    override fun content(mobileDevice: MobileDevice) {
        var deepLinkDialogVisible by remember { mutableStateOf(false) }

        Column(
            modifier = Modifier
                .fillMaxSize(1f),

            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            if (deepLinkDialogVisible) {
                OpenDeepLinkDialog(
                    onClose = {
                        deepLinkDialogVisible = false
                    },
                    onSend = {
                        GlobalScope.launch {
                            mobileDevice.openDeepLink(it)
                        }
                    }
                )
            }

            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(Color(255, 128, 31))
                    .aspectRatio(1F)
                    .weight(1F)
                    .clickable {
                        deepLinkDialogVisible = true
                    },

                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource("icons/actions/link.svg"),
                    contentDescription = "DeepLink Icon",
                    tint = Color.White,
                    modifier = Modifier.size(28.dp)
                )
            }

            Text(
                text = "Open Deep-Link",
                maxLines = 1,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }

    @OptIn(DelicateCoroutinesApi::class, ExperimentalMaterial3Api::class)
    @Composable
    private fun OpenDeepLinkDialog(onClose: () -> Unit, onSend: (String) -> Unit) {
        var isDialogVisible by remember { mutableStateOf(false ) }
        var deepLinkURL by remember  { mutableStateOf("") }

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
                                .width(280.dp)
                        ) {
                            Text(
                                text = "Open a Deep-Link",
                                fontSize = 22.sp,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .fillMaxWidth(),
                                color = MaterialTheme.colorScheme.onBackground
                            )
                            Spacer(Modifier.height(12.dp))

                            OutlinedTextField(
                                value = deepLinkURL,
                                onValueChange = { deepLinkURL = it },
                                label = { Text("Deep Link") }
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
                                    Text("Cancel")
                                }

                                TextButton(
                                    onClick = {
                                        isDialogVisible = false

                                        GlobalScope.launch {
                                            onSend(deepLinkURL)
                                            delay(250)
                                            onClose()
                                        }
                                    }
                                ) {
                                    Text("Open")
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