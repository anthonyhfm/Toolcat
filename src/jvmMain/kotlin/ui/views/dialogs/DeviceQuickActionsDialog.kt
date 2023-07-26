package ui.views.dialogs

import androidx.compose.animation.*
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import mobile.MobileDevice

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
                            .background(MaterialTheme.colors.background)
                            .fillMaxHeight()
                            .width(320.dp)
                            .padding(24.dp)
                    ) {

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