package dev.anthonyhfm.toolcat.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import dev.anthonyhfm.kit.desktop.WindowManager
import dev.anthonyhfm.kit.desktop.toasts.Toast
import dev.anthonyhfm.toolcat.core.cmd.CommandRegistry
import dev.anthonyhfm.toolcat.core.module.ModuleRegistry
import dev.anthonyhfm.toolcat.core.platform.android.AndroidDeviceRepository
import dev.anthonyhfm.toolcat.core.utils.Device
import dev.anthonyhfm.toolcat.core.utils.GlobalSettings
import dev.anthonyhfm.toolcat.main.theme.ToolcatTheme
import kotlinx.coroutines.*

@OptIn(DelicateCoroutinesApi::class)
fun main(args: Array<String>) = application {
    Device.createUserDir()
    ModuleRegistry.loadModules()

    GlobalSettings.loadGlobalSettings()
    CommandRegistry.executeCommand(args.toList())

    Window(
        onCloseRequest = ::exitApplication,
        title = "Toolcat",
    ) {
        WindowManager.configure(window)

        ToolcatTheme.runCompose {
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Column {
                    WindowManager.platformPadding()

                    ToolcatMainView()
                }

                Toast.ToastEngine(modifier = Modifier.align(Alignment.BottomCenter))
            }
        }

        GlobalScope.launch(Dispatchers.IO) {
            while (true) {
                AndroidDeviceRepository.fetchDevices()
                AndroidDeviceRepository.fetchEmulators()

                delay(250)
            }
        }
    }
}
