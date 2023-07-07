import androidx.compose.material.MaterialTheme
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import commands.CommandRegistry
import mobile.MobileDeviceRepository
import ui.views.MainView

@Composable
@Preview
fun App() {
    MaterialTheme {
        MainView()
    }
}

fun main(args: Array<String>) = application {
    CommandRegistry().executeCommand(args.toList())

    Window(onCloseRequest = ::exitApplication, title = "Toolcat by Anthony Hofmeister") {
        App()
    }
}
