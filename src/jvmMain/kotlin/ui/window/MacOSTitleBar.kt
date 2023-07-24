package ui.window

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.window.WindowDraggableArea
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.FrameWindowScope
import androidx.compose.ui.zIndex
import kotlin.system.exitProcess

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MacOSTitleBar(window: FrameWindowScope) {
    var hoveringButtons by remember { mutableStateOf(false) }
    var closeButtonDown by remember { mutableStateOf(false) }
    var minimizeButtonDown by remember { mutableStateOf(false) }

    window.WindowDraggableArea {
        Box(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primary)
                .fillMaxWidth()
                .height(30.dp)
        ) {
            Row(
                modifier = Modifier
                    .zIndex(0F)
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .weight(1F)
                        .fillMaxHeight()
                        .padding(start = 12.dp),

                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = window.window.title,
                        color = Color.White,
                        fontSize = 14.sp
                    )
                }
            }

            Row(
                modifier = Modifier
                    .zIndex(1F)
                    .onPointerEvent(PointerEventType.Enter) { hoveringButtons = true }
                    .onPointerEvent(PointerEventType.Exit) { hoveringButtons = false }
                    .padding(9.dp),

                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(
                            if (closeButtonDown)
                                Color(217, 87, 76)
                            else
                                Color(237, 107, 96)
                        )
                        .size(12.dp)
                        .onPointerEvent(PointerEventType.Press) {
                            closeButtonDown = true
                        }
                        .onPointerEvent(PointerEventType.Release) {
                            closeButtonDown = false
                            exitProcess(0)
                        },

                    contentAlignment = Alignment.Center
                ) {
                    if (hoveringButtons) {
                        Icon(
                            painterResource("icons/titlebar/mac/close.svg"),
                            contentDescription = null,
                            tint = Color.Black.copy(alpha = 0.6F),
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(
                            if (minimizeButtonDown)
                                Color(225, 174, 60)
                            else
                                Color(245, 194, 80)
                        )
                        .size(12.dp)
                        .onPointerEvent(PointerEventType.Press) {
                            minimizeButtonDown = true
                        }
                        .onPointerEvent(PointerEventType.Release) {
                            window.window.isMinimized = true
                            minimizeButtonDown = false
                        },

                    contentAlignment = Alignment.Center
                ) {
                    if (hoveringButtons) {
                        Icon(
                            painterResource("icons/titlebar/mac/minimize.svg"),
                            contentDescription = null,
                            tint = Color.Black.copy(alpha = 0.5F)
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(Color(216, 216, 218))
                        .size(12.dp)
                )
            }
        }
    }
}