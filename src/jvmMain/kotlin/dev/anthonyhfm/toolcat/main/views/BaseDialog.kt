package dev.anthonyhfm.toolcat.main.views

import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Popup

@Composable
fun Dialog(onClose: () -> Unit, content: @Composable () -> Unit) {
    Popup(
        onDismissRequest = {
            onClose()
        }
    ) {
        content()
    }
}
