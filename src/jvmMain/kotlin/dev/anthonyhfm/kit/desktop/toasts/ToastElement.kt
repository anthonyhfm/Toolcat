package dev.anthonyhfm.kit.desktop.toasts

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.*
import androidx.compose.animation.slideIn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.anthonyhfm.toolcat.main.theme.Inter
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
internal fun ToastElement(toastBase: ToastBase, onDismiss: () -> Unit) {
    var enableInitialAnimation: Boolean by remember { mutableStateOf(false) }
    var showOtherElements: Boolean by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    val initialAnimationFactor = animateFloatAsState(
        targetValue = if (!enableInitialAnimation) 0f else 1f,
        animationSpec = tween(
            durationMillis = 300,
            easing = EaseInOutSine
        )
    )

    LaunchedEffect(Unit) {
        enableInitialAnimation = true
        delay(600)
        showOtherElements = true
    }

    Row(
        modifier = Modifier
            .scale(initialAnimationFactor.value)
            .padding(bottom = 16.dp * initialAnimationFactor.value)
            .clip(RoundedCornerShape(24.dp))
            .background(toastBase.color),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Icon(
            painter = painterResource(toastBase.icon),
            contentDescription = null,
            tint = toastBase.iconColor,
            modifier = Modifier
                .padding(12.dp)
                .size(24.dp)
        )

        AnimatedVisibility(
            visible = showOtherElements,
        ) {
            Column {
                Text(
                    text = toastBase.text,
                    color = toastBase.textColor,
                    fontFamily = Inter,
                    fontSize = 16.sp,
                    lineHeight = 16.sp,
                    modifier = Modifier.padding(vertical = 14.dp),
                )
            }
        }

        AnimatedVisibility(showOtherElements) {
            IconButton(
                onClick = {
                    scope.launch {
                        showOtherElements = false
                        enableInitialAnimation = false
                        delay(300)
                        onDismiss()
                    }
                }
            ) {
                Icon(Icons.Default.Close, null, tint = toastBase.iconColor)
            }
        }
    }
}