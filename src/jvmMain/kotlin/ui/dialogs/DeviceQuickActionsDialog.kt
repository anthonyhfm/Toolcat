package ui.dialogs

import androidx.compose.animation.*
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.*
import mobile.DeviceType
import mobile.MobileDevice
import mobile.getName
import quickactions.QuickActionAvailability
import quickactions.QuickActionSize
import quickactions.quickActionList

@Composable
fun QuickActionsList(mobileDevice: MobileDevice) {
    val largeActions = quickActionList.filter {
        when (mobileDevice.deviceType) {
            DeviceType.ANDROID -> {
                it.actionSize == QuickActionSize.LARGE && (it.availability == QuickActionAvailability.ANDROID || it.availability == QuickActionAvailability.BOTH)
            }

            DeviceType.IOS -> {
                it.actionSize == QuickActionSize.LARGE && (it.availability == QuickActionAvailability.IOS || it.availability == QuickActionAvailability.BOTH)
            }
        }
    }

    val smallActions = quickActionList.filter {
        when (mobileDevice.deviceType) {
            DeviceType.ANDROID -> {
                it.actionSize == QuickActionSize.SMALL && (it.availability == QuickActionAvailability.ANDROID || it.availability == QuickActionAvailability.BOTH)
            }

            DeviceType.IOS -> {
                it.actionSize == QuickActionSize.SMALL && (it.availability == QuickActionAvailability.IOS || it.availability == QuickActionAvailability.BOTH)
            }
        }
    }

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(largeActions) {
            Row(
                modifier = Modifier
                    .height(100.dp),
            ) {
                Box(Modifier.weight(1F)) {
                    it.content(mobileDevice)
                }
            }
        }

        items(smallActions.count()) {
            if (it % 2 == 0) {
                Row(
                    modifier = Modifier
                        .height(100.dp),

                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Box(Modifier.weight(1F)) {
                        smallActions[it].content(mobileDevice)
                    }

                    if (smallActions.getOrNull(it + 1) != null) {
                        Box(Modifier.weight(1F)) {
                            smallActions[it + 1].content(mobileDevice)
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class, DelicateCoroutinesApi::class)
@Composable
fun DeviceQuickActionsDialog(mobileDevice: MobileDevice, onClose: () -> Unit) {
    var isDialogVisible by remember { mutableStateOf(false) }
    var isSheetVisible by remember { mutableStateOf(false) }

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
            Row(
                modifier = Modifier
                    .background(Color.Black.copy(alpha = 0.4f))
                    .fillMaxSize(),

                verticalAlignment = Alignment.Bottom,
            ) {
                Box(
                    modifier = Modifier
                        .weight(1F)
                        .fillMaxHeight()
                        .onPointerEvent(PointerEventType.Release) {
                            GlobalScope.launch {
                                isSheetVisible = false
                                isDialogVisible = false
                                delay(250)
                                onClose()
                            }
                        },
                )

                AnimatedVisibility(
                    visible = isSheetVisible,
                ) {
                    Box(
                        modifier = Modifier
                            .background(MaterialTheme.colorScheme.background)
                            .fillMaxHeight()
                            .width(320.dp)
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
                                            GlobalScope.launch {
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
                                            val job = GlobalScope.launch(Dispatchers.Default) {
                                                name = mobileDevice.getName()
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

                            QuickActionsList(mobileDevice)
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