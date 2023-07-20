package ui.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Tooltip(tip: String, content: @Composable () -> Unit) {
    TooltipArea(
        tooltip = {
            Text(
                text = tip,
                color = Color.White,
                modifier = Modifier
                    .padding(top = 6.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(Color.Black.copy(alpha = 0.8F))
                    .padding(horizontal = 12.dp, vertical = 6.dp)
            )
        },
        tooltipPlacement = TooltipPlacement.ComponentRect()
    ) {
        content()
    }
}