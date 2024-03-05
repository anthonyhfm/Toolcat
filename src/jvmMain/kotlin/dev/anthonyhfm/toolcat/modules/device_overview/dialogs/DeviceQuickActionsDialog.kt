package dev.anthonyhfm.toolcat.modules.device_overview.dialogs

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import dev.anthonyhfm.toolcat.core.platform.android.AndroidDevice
import dev.anthonyhfm.toolcat.core.platform.android.system.name
import dev.anthonyhfm.toolcat.modules.device_overview.views.QuickActionListView
import kotlinx.coroutines.*
import ui.dialogs.Dialog

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun DeviceQuickActionsDialog(device: Any, onClose: () -> Unit) {
    val scope = rememberCoroutineScope()
    var isDialogVisible by remember { mutableStateOf(false) }
    var isSheetVisible by remember { mutableStateOf(false) }
    var mouseOnDialog by remember { mutableStateOf(false) }

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
            Row(
                modifier = Modifier
                    .background(Color.Black.copy(alpha = 0.4f))
                    .fillMaxSize()
                    .onPointerEvent(PointerEventType.Press) {
                        if (!mouseOnDialog) {
                            scope.launch {
                                isSheetVisible = false
                                isDialogVisible = false
                                delay(250)
                                onClose()
                            }
                        }
                    },

                horizontalArrangement = Arrangement.End
            ) {
                AnimatedVisibility(
                    visible = isSheetVisible,
                ) {
                    Box(
                        modifier = Modifier
                            .background(MaterialTheme.colorScheme.background)
                            .fillMaxHeight()
                            .width(320.dp)
                            .onPointerEvent(PointerEventType.Enter) {
                                mouseOnDialog = true
                            }
                            .onPointerEvent(PointerEventType.Exit) {
                                mouseOnDialog = false
                            }
                            .padding(16.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                        ) {
                            Column(
                                modifier = Modifier
                                    .height(80.dp),

                                verticalArrangement = Arrangement.Center
                            ) {
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                                ) {
                                    IconButton(
                                        onClick = {
                                            scope.launch {
                                                isSheetVisible = false
                                                isDialogVisible = false
                                                delay(250)
                                                onClose()
                                            }
                                        }
                                    ) {
                                        Icon(Icons.Default.ArrowBack, null, tint = MaterialTheme.colorScheme.onBackground)
                                    }

                                    Column(
                                        modifier = Modifier
                                            .padding(bottom = 24.dp)
                                    ) {
                                        var name by remember { mutableStateOf<String>("") }

                                        DisposableEffect(Unit) {
                                            val job = scope.launch(Dispatchers.Default) {
                                                name = when (device) {
                                                    is AndroidDevice -> { device.name }

                                                    else -> TODO("Device Name Fetch not implemented (DeviceQuickActionsDialog)")
                                                }
                                            }
                                            onDispose { job.cancel() }
                                        }

                                        Text(
                                            text = "Quick Actions",
                                            fontSize = 28.sp,
                                            color = MaterialTheme.colorScheme.onBackground
                                        )
                                        Text(
                                            text = name,
                                            fontSize = 12.sp,
                                            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
                                        )
                                    }
                                }
                            }

                            Spacer(Modifier.height(20.dp))

                            QuickActionListView(device)
                        }
                    }
                }

                LaunchedEffect(Unit) {
                    delay(100)
                    isSheetVisible = true
                }
            }
        }

        LaunchedEffect(Unit) {
            isDialogVisible = true
        }
    }
}
