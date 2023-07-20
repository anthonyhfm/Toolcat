import androidx.compose.material.MaterialTheme
import androidx.compose.ui.awt.ComposeWindow
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import commands.CommandRegistry
import ui.views.MainView

var composeWindow: ComposeWindow? = null

fun main(args: Array<String>) = application {
    CommandRegistry().executeCommand(args.toList())

    Window(onCloseRequest = ::exitApplication, title = "Toolcat by Anthony Hofmeister") {
        composeWindow = this.window

        MaterialTheme {
            MainView()
        }
    }
}
