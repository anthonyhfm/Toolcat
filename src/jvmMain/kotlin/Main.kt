import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import commands.CommandRegistry
import settings.GlobalSettings
import ui.views.MainView
import utils.WindowHandler

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
            MaterialTheme {
                MainView()
            }
        }
    }
}
