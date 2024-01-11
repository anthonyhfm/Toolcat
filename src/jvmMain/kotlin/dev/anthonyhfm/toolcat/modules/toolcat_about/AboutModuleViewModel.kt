package dev.anthonyhfm.toolcat.modules.toolcat_about

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import dev.anthonyhfm.toolcat.core.module.ModuleViewModel

object AboutModuleViewModel : ModuleViewModel {
    override val name: String = "About"
    override val iconResource: String = "icons/info_filled.svg"

    @Composable
    override fun ModuleView() {
        Box(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            Image(
                painter = painterResource("icons/toolcat-logo.png"),
                contentDescription = null,
                modifier = Modifier
                    .height(180.dp)
                    .align(Alignment.Center)
            )

            Text(
                text = "Made with \uD83D\uDC9A by Anthony Hofmeister",
                modifier = Modifier
                    .padding(vertical = 24.dp)
                    .align(Alignment.BottomCenter)
            )
        }
    }
}
