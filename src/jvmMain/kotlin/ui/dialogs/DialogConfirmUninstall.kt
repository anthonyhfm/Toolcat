package ui.dialogs

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.*
import mobile.firmware.MobileApplication

@OptIn(DelicateCoroutinesApi::class)
@Composable
fun DialogConfirmAppUninstall(
    application: MobileApplication,
    onClose: () -> Unit,
    onConfirm: () -> Unit
) {
    var isDialogVisible by remember { mutableStateOf(false ) }

    Dialog(
        onClose = {
            onClose()
        }
    ) {
        AnimatedVisibility(
            visible = isDialogVisible,
            enter = fadeIn(
                initialAlpha = 0f
            ),
            exit = fadeOut(
                animationSpec = tween(durationMillis = 250),
                targetAlpha = 0f
            )
        ) {
            Box(
                modifier = Modifier
                    .background(Color.Black.copy(alpha = 0.6f))
                    .fillMaxSize(),

                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(24.dp))
                        .background(MaterialTheme.colorScheme.background)
                        .padding(24.dp)
                ) {
                    Column(
                        horizontalAlignment = Alignment.End,
                        verticalArrangement = Arrangement.spacedBy(12.dp),

                        modifier = Modifier
                            .width(300.dp)
                    ) {
                        Text(
                            text = "Uninstall App",
                            fontSize = 22.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth(),
                            color = MaterialTheme.colorScheme.onBackground
                        )
                        Spacer(Modifier.height(12.dp))

                        Text(
                            text = "Are you sure that you want to uninstall ${application.id}?",
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colorScheme.onBackground
                        )

                        Row(
                            horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.End)
                        ) {
                            TextButton(
                                onClick = {
                                    isDialogVisible = false

                                    GlobalScope.launch {
                                        delay(250)
                                        onClose()
                                    }
                                }
                            ) {
                                Text("Close")
                            }

                            Button(
                                onClick = {
                                    isDialogVisible = false

                                    GlobalScope.launch {
                                        delay(250)
                                        onConfirm()
                                    }
                                }
                            ) {
                                Text("Uninstall")
                            }
                        }
                    }
                }
            }
        }

        LaunchedEffect(Unit) {
            isDialogVisible = true
        }
    }
}
