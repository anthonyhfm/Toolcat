package dev.anthonyhfm.toolcat.main

import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import dev.anthonyhfm.toolcat.commandline.CommandRegistry
import dev.anthonyhfm.toolcat.core.platform.android.AndroidDeviceRepository
import dev.anthonyhfm.toolcat.core.utils.GlobalSettings
import dev.anthonyhfm.toolcat.main.theme.ToolcatTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ui.views.MainView

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

        scope.launch(Dispatchers.IO) {
            while (true) {
                AndroidDeviceRepository.fetchConnectedDevices()

                delay(100)
            }
        }
    }
}
