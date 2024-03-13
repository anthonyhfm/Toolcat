package dev.anthonyhfm.kit.desktop.toasts

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

data class ToastBase(
    val color: Color,
    val textColor: Color,
    val iconColor: Color,
    val icon: String,
    val text: String
)

object Toast {
    private var currentToast: MutableState<ToastBase?> = mutableStateOf(null)

    @Composable
    fun ToastEngine(modifier: Modifier = Modifier) {
        Box(
            modifier = modifier
                .fillMaxWidth(0.8f),

            contentAlignment = Alignment.BottomCenter
        ) {
            if (currentToast.value != null) {
                ToastElement(currentToast.value!!) {
                    currentToast.value = null
                }
            }
        }
    }

    fun infoToast(text: String) {
        currentToast.value = null

        currentToast.value = ToastBase(
            color = Color.Black,
            textColor = Color.White,
            iconColor = Color.White,
            icon = "icons/info_filled.svg",
            text = text
        )
    }

    fun warningToast(text: String) {
        TODO("Not implemented")
    }

    fun errorToast(text: String) {
        TODO("Not implemented")
    }
}