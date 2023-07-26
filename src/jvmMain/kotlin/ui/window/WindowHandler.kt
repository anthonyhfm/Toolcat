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
    var titleBarOffset: Int = 0

    fun useCustomDecoration(): Boolean {
        val os = System.getProperty("os.name").lowercase(Locale.getDefault())

        return os.contains("windows") || os.contains("mac os x")
    }

    @Composable
    fun windowContent(window: FrameWindowScope, content: @Composable () -> Unit) {
        if (useCustomDecoration()) {
            val os = System.getProperty("os.name").lowercase(Locale.getDefault())
            val isMacOS = os.contains("mac os x")

            titleBarOffset = if (isMacOS) 60 else 32

            Column(
                modifier = Modifier.clip(RoundedCornerShape(if (isMacOS) 12.dp else 8.dp))
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