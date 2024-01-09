package ui.dialogs

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.*
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupPositionProvider
import kotlin.math.roundToInt

@ExperimentalComposeUiApi
class BaseModelPopupPositionProvider : PopupPositionProvider {
    val positionPx: Offset = Offset(0F, 0F)
    val offsetPx: Offset = Offset(0F, 0F)
    val isRelativeToAnchor: Boolean = false
    val alignment: Alignment = Alignment.BottomEnd

    override fun calculatePosition(
        anchorBounds: IntRect,
        windowSize: IntSize,
        layoutDirection: LayoutDirection,
        popupContentSize: IntSize
    ): IntOffset {
        val anchor = IntRect(
            offset = positionPx.round() +
                    (if (isRelativeToAnchor) anchorBounds.topLeft else IntOffset.Zero),
            size = IntSize.Zero)
        val tooltipArea = IntRect(
            IntOffset(
                anchor.left - popupContentSize.width,
                anchor.top - popupContentSize.height,
            ),
            IntSize(
                popupContentSize.width * 2,
                popupContentSize.height * 2
            )
        )
        val position = alignment.align(popupContentSize, tooltipArea.size, layoutDirection)
        var x = tooltipArea.left + position.x + offsetPx.x
        var y = tooltipArea.top + position.y + offsetPx.y
        if (x + popupContentSize.width > windowSize.width) {
            x -= popupContentSize.width
        }
        if (y + popupContentSize.height > windowSize.height) {
            y -= popupContentSize.height + anchor.height
        }
        x = x.coerceAtLeast(0.toFloat())
        y = y.coerceAtLeast(0f)

        return IntOffset(x.roundToInt(), y.roundToInt())
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun BaseDialog(content: @Composable () -> Unit) {
    Popup(
        popupPositionProvider = BaseModelPopupPositionProvider(),
        focusable = true
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            content()
        }
    }
}
