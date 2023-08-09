package ui.components

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlin.math.abs

@Composable
fun LoadingAnimation() {
    val infiniteTransition = rememberInfiniteTransition()

    val animatedProgress by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 650),
            repeatMode = RepeatMode.Restart
        )
    )

    Box(
        modifier = Modifier
            .size(80.dp)
    ) {
        val shift = 54.dp
        val scaleShift = 1f + abs(0.5 - animatedProgress).toFloat()

        Row(
            modifier = Modifier
                .align(Alignment.TopStart)
                .fillMaxWidth()
                .height(26.dp)
        ) {
            Box(
                modifier = Modifier
                    .offset(x = shift * animatedProgress)
                    .rotate(180 * animatedProgress)
                    .padding(6.dp)
                    .size(14.dp)
                    .scale(scaleShift)
                    .background(MaterialTheme.colorScheme.primary)
            )
        }

        Column(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .fillMaxHeight()
                .width(26.dp)
        ) {
            Box(
                modifier = Modifier
                    .offset(y = shift * animatedProgress)
                    .rotate(180 * animatedProgress)
                    .padding(6.dp)
                    .size(14.dp)
                    .scale(scaleShift)
                    .background(MaterialTheme.colorScheme.primary)
            )
        }

        Row(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .fillMaxWidth()
                .height(26.dp),

            horizontalArrangement = Arrangement.End
        ) {
            Box(
                modifier = Modifier
                    .offset(x = - shift * animatedProgress)
                    .rotate(180 * animatedProgress)
                    .padding(6.dp)
                    .size(14.dp)
                    .scale(scaleShift)
                    .background(MaterialTheme.colorScheme.primary)
            )
        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .fillMaxHeight()
                .width(26.dp),

            verticalArrangement = Arrangement.Bottom
        ) {
            Box(
                modifier = Modifier
                    .offset(y = - shift * animatedProgress)
                    .rotate(180 * animatedProgress)
                    .padding(6.dp)
                    .size(14.dp)
                    .scale(scaleShift)
                    .background(MaterialTheme.colorScheme.primary)
            )
        }
    }
}