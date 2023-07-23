package ui.window

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.ComposeWindow
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.FrameWindowScope
import java.util.*

object WindowHandler {
    fun useCustomDecoration(): Boolean {
        val os = System.getProperty("os.name").lowercase(Locale.getDefault())

        return os.contains("windows") || os.contains("mac os x")
    }

    @Composable
    fun windowContent(window: FrameWindowScope, content: @Composable () -> Unit) {
        if (useCustomDecoration()) {
            Column(
                modifier = Modifier.clip(RoundedCornerShape(8.dp))
            ) {
                val os = System.getProperty("os.name").lowercase(Locale.getDefault())

                if (os.contains("windows")) {
                    WindowsTitleBar(window)
                }

                content()
            }
        }
        else {
            content()
        }
    }
}