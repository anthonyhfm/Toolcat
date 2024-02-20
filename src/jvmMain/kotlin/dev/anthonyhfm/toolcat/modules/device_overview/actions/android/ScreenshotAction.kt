package dev.anthonyhfm.toolcat.modules.device_overview.actions.android

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toComposeImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.*
import dev.anthonyhfm.toolcat.core.platform.android.AndroidDevice
import dev.anthonyhfm.toolcat.core.platform.android.system.getScreenshot
import dev.anthonyhfm.toolcat.core.platform.android.system.name
import dev.anthonyhfm.toolcat.modules.device_overview.actions.QuickActionModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.awt.image.BufferedImage
import javax.imageio.ImageIO

class ScreenshotAction(override val device: AndroidDevice) : QuickActionModel<AndroidDevice>(device) {
    @Composable
    override fun Content() {
        val scope = rememberCoroutineScope()
        var image by remember { mutableStateOf<BufferedImage?>(null) }
        var showImage by remember { mutableStateOf(false) }

        Column(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .padding(vertical = 8.dp)
                .aspectRatio(1f / 1f),

            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically)
        ) {
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(Color(145, 77, 255))
                    .size(64.dp)
                    .clickable {
                        scope.launch(Dispatchers.IO) {
                            val inputStream = device.getScreenshot()

                            image = ImageIO.read(inputStream)
                            showImage = true
                        }
                    },

                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource("icons/actions/screenshot.svg"),
                    contentDescription = "Screenshot Icon",
                    tint = Color.White,
                    modifier = Modifier.size(28.dp)
                )
            }

            Text(
                text = "Screenshot",
                maxLines = 1,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onBackground
            )
        }

        if (showImage) {
            image?.let {
                ScreenshotPreviewWindow(
                    image = image!!,
                    onClose = {
                        showImage = false
                        image = null
                    }
                )
            }
        }
    }

    @Composable
    private fun ScreenshotPreviewWindow(image: BufferedImage, onClose: () -> Unit) {
        val dialogState: DialogState by remember {
            mutableStateOf(
                DialogState(
                    size = DpSize(image.width.dp / 2, image.height.dp / 2)
                )
            )
        }

        DialogWindow(
            onCloseRequest = {
                onClose()
            },
            title = "Screenshot [${device.name}]",
            state = dialogState
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White),

                contentAlignment = Alignment.Center,
            ) {
                Image(
                    bitmap = image.toComposeImageBitmap(),
                    contentDescription = "Screenshot",
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}
