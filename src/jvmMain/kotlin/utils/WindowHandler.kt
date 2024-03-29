package utils

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.FrameWindowScope
import settings.GlobalSettings
import ui.window.MacOSTitleBar
import ui.window.WindowsTitleBar
import java.util.*

object WindowHandler {
    var titleBarOffset: Int = 0
    var windowCornerRadius: Int = 0

    fun useCustomDecoration(): Boolean {
        return when (Device.getOS()) {
            OperatingSystem.WINDOWS -> true
            OperatingSystem.MACOS -> true

            else -> false
        }
    }

    @Composable
    fun windowContent(window: FrameWindowScope, content: @Composable () -> Unit) {
        if (useCustomDecoration() && GlobalSettings.customTitleBar.value) {
            val os = System.getProperty("os.name").lowercase(Locale.getDefault())
            val isMacOS = os.contains("mac os x")

            titleBarOffset = if (isMacOS) 60 else 32
            windowCornerRadius = if (isMacOS) 12 else 8

            Column(
                modifier = Modifier.clip(RoundedCornerShape(windowCornerRadius.dp))
            ) {

                if (isMacOS.not()) {
                    WindowsTitleBar(window)
                }
                else {
                    MacOSTitleBar(window)
                }

                content()
            }
        }
        else {
            content()
        }
    }
}
