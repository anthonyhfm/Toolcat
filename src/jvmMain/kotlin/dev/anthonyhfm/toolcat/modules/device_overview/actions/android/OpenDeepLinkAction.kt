package dev.anthonyhfm.toolcat.modules.device_overview.actions.android

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.anthonyhfm.toolcat.core.platform.android.AndroidDevice
import dev.anthonyhfm.toolcat.core.platform.android.system.openDeepLink
import dev.anthonyhfm.toolcat.main.theme.Inter
import dev.anthonyhfm.toolcat.main.views.Dialog
import dev.anthonyhfm.toolcat.modules.device_overview.actions.QuickActionModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class OpenDeepLinkAction(override val device: AndroidDevice) : QuickActionModel<AndroidDevice>(device) {
    @Composable
    override fun Content() {
        val scope = rememberCoroutineScope()
        var deepLinkDialogVisible by remember { mutableStateOf(false) }

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

                if (deepLinkDialogVisible) {
                    OpenDeepLinkDialog(
                        onClose = {
                            deepLinkDialogVisible = false
                        },
                        onSend = {
                            scope.launch {
                                device.openDeepLink(it)
                            }
                        }
                    )
                }
            }

            Text(
                text = "Open Deeplink",
                maxLines = 1,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }

    @OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
    @Composable
    private fun OpenDeepLinkDialog(onClose: () -> Unit, onSend: (String) -> Unit) {
        val scope = rememberCoroutineScope()
        var isDialogVisible by remember { mutableStateOf(false ) }
        var deepLinkURL by remember  { mutableStateOf("") }

        Dialog(
            onClose = {
                onClose()
            }
        ) {
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
                                fontFamily = Inter,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .fillMaxWidth(),
                                color = MaterialTheme.colorScheme.onBackground
                            )
                            Spacer(Modifier.height(12.dp))

                            OutlinedTextField(
                                value = deepLinkURL,
                                singleLine = true,
                                onValueChange = { deepLinkURL = it },
                                label = { Text("Deep Link") },
                                modifier = Modifier
                                    .onKeyEvent {
                                        if (it.key.keyCode == Key.Enter.keyCode) {
                                            if (deepLinkURL.isNotBlank() && deepLinkURL.isNotEmpty()) {
                                                isDialogVisible = false

                                                scope.launch {
                                                    onSend(deepLinkURL)
                                                    delay(250)
                                                    onClose()
                                                }
                                            }
                                        }

                                        return@onKeyEvent true
                                    }
                            )

                            Row(
                                horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.End)
                            ) {
                                TextButton(
                                    onClick = {
                                        isDialogVisible = false

                                        scope.launch {
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

                                        scope.launch {
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
