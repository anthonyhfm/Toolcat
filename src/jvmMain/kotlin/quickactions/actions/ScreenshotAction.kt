package quickactions.actions

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toComposeImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import mobile.MobileDevice
import mobile.firmware.disableWifi
import mobile.firmware.enableWifi
import mobile.firmware.getScreenshot
import mobile.firmware.getWiFiActivated
import quickactions.QuickAction
import quickactions.QuickActionAvailability
import quickactions.QuickActionSize
import org.jetbrains.skiko.toBitmap
import utils.TransferableImage
import java.awt.Toolkit
import java.awt.image.BufferedImage
import java.io.InputStream
import javax.imageio.ImageIO

class ScreenshotAction : QuickAction {
    override val actionSize: QuickActionSize = QuickActionSize.SMALL
    override val availability = listOf(
        QuickActionAvailability.ANDROID,
        QuickActionAvailability.ANDROID_SIM,
    )

    @OptIn(DelicateCoroutinesApi::class)
    @Composable
    override fun content(mobileDevice: MobileDevice) {
        var image by remember { mutableStateOf<BufferedImage?>(null) }
        var showImage by remember { mutableStateOf(false) }

        Column(
            modifier = Modifier
                .fillMaxSize(1f),

            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(Color(145, 77, 255))
                    .aspectRatio(1F)
                    .weight(1F)
                    .clickable {
                        GlobalScope.launch {
                            val inputStream = mobileDevice.getScreenshot()

                            if (inputStream != null) {
                                image = ImageIO.read(inputStream)
                                showImage = true
                            }
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
        Window(
            visible = true,
            onCloseRequest = { onClose() },
            title = "Screenshot",
            state = WindowState(width = image.width.dp / 3, height = image.height.dp / 3 + 60.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black),

                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Image(bitmap = image.toComposeImageBitmap(), "Screenshot", modifier = Modifier.weight(1F))

                Row(
                    modifier = Modifier
                        .height(60.dp)
                        .fillMaxWidth(),

                    horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterHorizontally),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = {
                            val transferable = TransferableImage(image)

                            Toolkit.getDefaultToolkit().systemClipboard.setContents(transferable, null)
                        }
                    ) {
                        Text("Copy to Clipboard")
                    }
                }
            }
        }
    }
}