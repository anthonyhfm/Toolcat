package dev.anthonyhfm.toolcat.main.views

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.onDrag
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import kotlinx.coroutines.launch

@OptIn(ExperimentalComposeUiApi::class, ExperimentalFoundationApi::class)
@Composable
fun VerticalScrollColumn(
    modifier: Modifier = Modifier,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    content: @Composable ColumnScope.() -> Unit
) {
    var scrollState = rememberScrollState()
    val scope = rememberCoroutineScope()
    val canScroll: Boolean = scrollState.canScrollForward || scrollState.canScrollBackward

    Row(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f)
                .verticalScroll(scrollState),

            verticalArrangement = verticalArrangement,
            horizontalAlignment = horizontalAlignment,
        ) {
            content()
        }

        if (canScroll) {
            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(18.dp)
                    .padding(5.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary.copy(0.2f))
            ) {
                val boxHeight = maxHeight * (maxHeight / (maxHeight.value + scrollState.maxValue)).value
                val boxOffset = (boxHeight / maxHeight) * scrollState.value

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(y = boxOffset.dp)
                        .height(boxHeight)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primary.copy(0.8f))
                        .onDrag {
                            val maxBoxOffset = (boxHeight / maxHeight) * scrollState.maxValue
                            val scrollSpace = maxHeight - maxBoxOffset.dp
                            val scroll = it.y / scrollSpace.value * scrollState.maxValue

                            scope.launch {
                                scrollState.scrollBy(scroll)
                            }
                        }
                )
            }
        }
    }
}
