import androidx.compose.runtime.Composable
import androidx.compose.ui.window.FrameWindowScope
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import commands.CommandRegistry
import kotlinx.coroutines.*
import mobile.MobileDeviceRepository
import settings.GlobalSettings
import ui.theme.ToolcatTheme
import ui.views.MainView
import utils.WindowHandler

@OptIn(DelicateCoroutinesApi::class)
@Composable
private fun WindowContent(scope: FrameWindowScope, content: @Composable () -> Unit) {
    WindowHandler.windowContent(scope) {
        content()
    }

    GlobalScope.launch {
        while (true) {
            MobileDeviceRepository.fetchConnectedDevices()

            delay(100)
        }
    }
}

@OptIn(DelicateCoroutinesApi::class)
fun main(args: Array<String>) = application {
    GlobalSettings.loadGlobalSettings()

    CommandRegistry().executeCommand(args.toList())

    if (GlobalSettings.customTitleBar.value) {
        Window(
            onCloseRequest = ::exitApplication,
            title = "Toolcat",
            undecorated = WindowHandler.useCustomDecoration(),
            transparent = WindowHandler.useCustomDecoration()
        ) {
            WindowContent(this) {
                ToolcatTheme.runCompose {
                    MainView()
                }
            }
        }
    } else {
        Window(
            onCloseRequest = ::exitApplication,
            title = "Toolcat",
        ) {
            WindowContent(this) {
                MainView()
            }
        }
    }
}
