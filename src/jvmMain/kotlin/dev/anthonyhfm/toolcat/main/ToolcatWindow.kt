package dev.anthonyhfm.toolcat.main

import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import dev.anthonyhfm.toolcat.commandline.CommandRegistry
import dev.anthonyhfm.toolcat.core.platform.android.AndroidDeviceRepository
import dev.anthonyhfm.toolcat.core.utils.GlobalSettings
import dev.anthonyhfm.toolcat.main.theme.ToolcatTheme
import kotlinx.coroutines.*
import kotlin.concurrent.thread

@OptIn(DelicateCoroutinesApi::class)
fun main(args: Array<String>) = application {
    GlobalSettings.loadGlobalSettings()
    CommandRegistry.executeCommand(args.toList())

    Window(
        onCloseRequest = ::exitApplication,
        title = "Toolcat",
    ) {
        val scope = rememberCoroutineScope()

        ToolcatTheme.runCompose {
            // MainView()
            ToolcatMainView()
        }

        scope.launch {
            while (true) {
                AndroidDeviceRepository.fetchDevices()
                AndroidDeviceRepository.fetchEmulators()

                delay(250)
            }
        }
    }
}
