package ui.window

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.window.WindowDraggableArea
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.FrameWindowScope
import settings.GlobalSettings
import ui.theme.ToolcatColorSchemeSet
import ui.theme.ToolcatTheme
import kotlin.system.exitProcess

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun WindowsTitleBarButton(
    normalColor: Color,
    hoverColor: Color = Color.Black.copy(alpha = 0.1F),
    iconPainter: Painter,
    iconSize: Dp,
    onClick: () -> Unit
) {
    var hovering by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .background(if (hovering) hoverColor else Color.Transparent)
            .fillMaxHeight()
            .width(45.dp)
            .onPointerEvent(PointerEventType.Enter) { hovering = true }
            .onPointerEvent(PointerEventType.Exit) { hovering = false }
            .onPointerEvent(PointerEventType.Release) {
                onClick()
            },

        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = iconPainter,
            contentDescription = "Title Bar Button",
            tint = normalColor,
            modifier = Modifier.size(iconSize)
        )
    }
}

@Composable
fun WindowsTitleBar(window: FrameWindowScope) {
    val currentThemeSet: ToolcatColorSchemeSet = ToolcatTheme.themeCollection[GlobalSettings.selectedTheme.value]

    val titleBarColors = if (GlobalSettings.enableDarkMode.value) {
        currentThemeSet.darkTitleBarColors
    } else {
        currentThemeSet.lightTitleBarColors
    }

    window.WindowDraggableArea {
        Row(
            modifier = Modifier
                .background(titleBarColors.background)
                .fillMaxWidth()
                .height(32.dp)
        ) {
            Row(
                modifier = Modifier
                    .weight(1F)
                    .fillMaxHeight()
                    .padding(start = 12.dp),

                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                Icon(
                    painter = painterResource("icons/toolcat-logo.png"),
                    contentDescription = "Toolcat Icon",
                    tint = titleBarColors.text,
                    modifier = Modifier.size(14.dp)
                )

                Text(
                    text = window.window.title,
                    color = titleBarColors.text,
                    letterSpacing = 1.sp
                )
            }

            Row(
                Modifier.fillMaxHeight()
            ) {
                WindowsTitleBarButton(
                    normalColor = titleBarColors.icons,
                    iconPainter = painterResource("icons/titlebar/win/minimize.svg"),
                    iconSize = 18.dp
                ) {
                    window.window.isMinimized = true
                }

                /*
                WindowsTitleBarButton(
                    iconPainter = painterResource("icons/titlebar/win/maximize.svg"),
                    iconSize = 16.dp
                ) {
                    TODO("There is no easy way to just maximize the Window")
                }
                 */

                WindowsTitleBarButton(
                    normalColor = titleBarColors.icons,
                    hoverColor = Color.Red.copy(alpha = 0.6F),
                    iconPainter = painterResource("icons/titlebar/win/close.svg"),
                    iconSize = 18.dp
                ) {
                    exitProcess(0)
                }
            }
        }
    }
}