package dev.anthonyhfm.kit.desktop

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.awt.ComposeWindow
import dev.anthonyhfm.toolcat.core.utils.Device
import dev.anthonyhfm.toolcat.core.utils.OperatingSystem

object WindowManager {
    fun configure(window: ComposeWindow) {
        when (Device.getOS()) {
            OperatingSystem.MACOS -> OSXWindow.macosCustomize(window)

            else -> return
        }
    }

    @Composable
    fun platformPadding() {
        val os by remember { mutableStateOf(Device.getOS()) }

        when (os) {
            OperatingSystem.MACOS -> OSXWindow.macosSpacer()

            else -> return
        }
    }
}