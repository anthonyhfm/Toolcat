package dev.anthonyhfm.toolcat.main

import androidx.compose.foundation.layout.Column
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import dev.anthonyhfm.kit.desktop.WindowManager
import dev.anthonyhfm.toolcat.core.cmd.CommandRegistry
import dev.anthonyhfm.toolcat.core.platform.android.AndroidDeviceRepository
import dev.anthonyhfm.toolcat.core.utils.GlobalSettings
import dev.anthonyhfm.toolcat.main.theme.ToolcatTheme
import kotlinx.coroutines.*

@OptIn(DelicateCoroutinesApi::class)
fun main(args: Array<String>) = application {
    GlobalSettings.loadGlobalSettings()
    CommandRegistry.executeCommand(args.toList())

    Window(
        onCloseRequest = ::exitApplication,
        title = "Toolcat"
    ) {
        WindowManager.configure(window)

        ToolcatTheme.runCompose {
            Column {
                WindowManager.platformPadding()

                ToolcatMainView()
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
