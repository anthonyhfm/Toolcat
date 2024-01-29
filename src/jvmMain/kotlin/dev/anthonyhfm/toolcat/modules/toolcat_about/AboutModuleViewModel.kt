package dev.anthonyhfm.toolcat.modules.toolcat_about

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.anthonyhfm.toolcat.core.module.ToolcatModule
import dev.anthonyhfm.toolcat.main.theme.Inter
import java.util.jar.JarFile

object AboutModuleViewModel : ToolcatModule {
    override val name: String = "About"
    override val iconResource: String = "icons/info_filled.svg"

    @Composable
    override fun ModuleView() {
        Box(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            Column(
                modifier = Modifier
                    .align(Alignment.Center),

                verticalArrangement = Arrangement.spacedBy(18.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource("icons/toolcat-logo.png"),
                    contentDescription = null,
                    modifier = Modifier
                        .height(180.dp)
                )

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    Text(
                        text = "TOOLCAT",
                        fontFamily = Inter,
                        fontWeight = FontWeight.ExtraLight,
                        fontSize = 32.sp,
                        letterSpacing = 4.sp,
                        color = MaterialTheme.colorScheme.onBackground
                    )

                    Text(
                        text = "Version 1.1.0",
                        fontFamily = Inter,
                        color = MaterialTheme.colorScheme.onBackground.copy(0.8f)
                    )
                }
            }

            Text(
                text = "Made with \uD83D\uDC9A by Anthony Hofmeister",
                modifier = Modifier
                    .padding(vertical = 24.dp)
                    .align(Alignment.BottomCenter)
            )
        }
    }
}
