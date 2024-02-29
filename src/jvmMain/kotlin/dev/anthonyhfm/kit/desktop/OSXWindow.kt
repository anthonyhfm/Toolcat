package dev.anthonyhfm.kit.desktop

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.ComposeWindow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

object OSXWindow {
    fun macosCustomize(window: ComposeWindow) {
        window.rootPane.putClientProperty("apple.awt.fullWindowContent", true );
        window.rootPane.putClientProperty("apple.awt.transparentTitleBar", true );
    }

    @Composable
    fun macosSpacer() {
        Spacer(
            modifier = Modifier
                .height(28.dp)
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
        )
    }
}