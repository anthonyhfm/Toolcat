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
fun main(args: Array<String>) = application {
    GlobalSettings.loadGlobalSettings()

    CommandRegistry().executeCommand(args.toList())

    Window(
        onCloseRequest = ::exitApplication,
        title = "Toolcat",
        undecorated = WindowHandler.useCustomDecoration(),
        transparent = WindowHandler.useCustomDecoration()
    ) {
        WindowHandler.windowContent(this) {
            ToolcatTheme.runCompose {
                MainView()
            }
        }

        GlobalScope.launch {
            while (true) {
                MobileDeviceRepository.fetchConnectedDevices()

                delay(100)
            }
        }
    }
}
