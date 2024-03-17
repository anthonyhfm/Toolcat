package dev.anthonyhfm.toolcat.modules.app_overview.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.anthonyhfm.kit.desktop.toasts.Toast
import dev.anthonyhfm.toolcat.core.platform.android.AndroidDevice
import dev.anthonyhfm.toolcat.core.platform.android.system.*
import dev.anthonyhfm.toolcat.main.theme.Inter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.lwjgl.util.tinyfd.TinyFileDialogs
import java.io.File

@Composable
internal fun LegacyAppPreview(device: AndroidDevice, appPackage: String, onTriggerReload: () -> Unit) {
    val scope = rememberCoroutineScope()
    var moreVisible: Boolean by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1F))
            .fillMaxWidth()
            .height(60.dp)
            .padding(10.dp),

        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier
                .weight(1f)
        ) {
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .fillMaxHeight()
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(horizontal = 16.dp),

                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = appPackage,
                    fontFamily = Inter,
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontSize = 18.sp,
                    lineHeight = 18.sp,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxHeight(),

            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            IconButton(
                onClick = {
                    scope.launch(Dispatchers.IO) {
                        device.openApp(appPackage)
                    }
                }
            ) {
                Icon(painterResource("icons/play_arrow.svg"), null, tint = MaterialTheme.colorScheme.onBackground)
            }

            IconButton(
                onClick = {

                }
            ) {
                Icon(Icons.Outlined.Info, null, tint = MaterialTheme.colorScheme.onBackground)
            }

            IconButton(
                onClick = {
                    moreVisible = true
                }
            ) {
                Icon(Icons.Default.MoreVert, null, tint = MaterialTheme.colorScheme.onBackground)

                AppMoreOptions(
                    visible = moreVisible,
                    onDismiss = {
                        moreVisible = false
                    },
                    device = device,
                    appPackage = appPackage,
                    onTriggerReload = {
                        moreVisible = false
                        onTriggerReload()
                    }
                )
            }
        }
    }
}

private fun saveAndroidPackageFile(appPackage: String, device: AndroidDevice): Boolean {
    val path = TinyFileDialogs.tinyfd_saveFileDialog("Download Android APK File", "$appPackage.apk", null, null)

    if (path != null) {
        device.saveApp(appPackage, File(path))
    }

    return path != null
}

@Composable
private fun AppMoreOptions(
    visible: Boolean,
    onDismiss: () -> Unit,
    device: AndroidDevice,
    appPackage: String,
    onTriggerReload: () -> Unit
) {
    val scope = rememberCoroutineScope()

    DropdownMenu(
        expanded = visible,
        onDismissRequest = {
            onDismiss()
        },
        modifier = Modifier.background(MaterialTheme.colorScheme.surface)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            DropdownMenuItem(
                modifier = Modifier
                    .height(42.dp)
                    .padding(horizontal = 8.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(MaterialTheme.colorScheme.secondaryContainer),
                contentPadding = PaddingValues(horizontal = 24.dp),
                text = {
                    Text(
                        text = "Download .apk",
                        fontFamily = Inter,
                        color = MaterialTheme.colorScheme.secondary
                    )
                },
                leadingIcon = {
                    Icon(painterResource("icons/download.svg"), null, tint = MaterialTheme.colorScheme.secondary)
                },
                onClick = {
                    val success = saveAndroidPackageFile(appPackage, device)

                    if (success) {
                        Toast.infoToast("The .apk file has been downloaded.")
                        onDismiss()
                    }
                }
            )

            DropdownMenuItem(
                modifier = Modifier
                    .height(42.dp)
                    .padding(horizontal = 8.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(MaterialTheme.colorScheme.secondaryContainer),
                contentPadding = PaddingValues(horizontal = 24.dp),
                text = {
                    Text(
                        text = "Clear all app data",
                        fontFamily = Inter,
                        color = MaterialTheme.colorScheme.secondary
                    )
                },
                leadingIcon = {
                    Icon(painterResource("icons/delete.svg"), null, tint = MaterialTheme.colorScheme.secondary)
                },
                onClick = {
                    device.clearAppStorage(appPackage)
                    onDismiss()
                }
            )

            DropdownMenuItem(
                modifier = Modifier
                    .height(42.dp)
                    .padding(horizontal = 8.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(MaterialTheme.colorScheme.secondaryContainer),
                contentPadding = PaddingValues(horizontal = 24.dp),
                text = {
                    Text(
                        text = "Process kill",
                        fontFamily = Inter,
                        color = MaterialTheme.colorScheme.secondary
                    )
                },
                leadingIcon = {
                    Icon(painterResource("icons/skull.svg"), null, tint = MaterialTheme.colorScheme.secondary)
                },
                onClick = {
                    scope.launch(Dispatchers.IO) {
                        device.killApp(appPackage)
                    }

                    onDismiss()
                }
            )

            Divider(Modifier.padding(horizontal = 16.dp))

            DropdownMenuItem(
                modifier = Modifier
                    .height(42.dp)
                    .padding(horizontal = 8.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(MaterialTheme.colorScheme.errorContainer),
                contentPadding = PaddingValues(horizontal = 24.dp),
                text = {
                    Text(
                        text = "Uninstall",
                        fontFamily = Inter,
                        color = MaterialTheme.colorScheme.error
                    )
                },
                leadingIcon = {
                    Icon(painterResource("icons/delete.svg"), null, tint = MaterialTheme.colorScheme.error)
                },
                onClick = {
                    scope.launch(Dispatchers.IO) {
                        device.removeApp(appPackage)

                        onTriggerReload()
                    }
                }
            )
        }
    }
}