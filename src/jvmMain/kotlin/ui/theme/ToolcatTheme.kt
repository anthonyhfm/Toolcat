package ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import kotlinx.coroutines.GlobalScope
import settings.GlobalSettings
import ui.theme.schemes.StandardColorScheme

object ToolcatTheme {
    val currentTheme: ToolcatColorSchemeSet = StandardColorScheme()

    @Composable
    fun runCompose(content: @Composable () -> Unit) {
        MaterialTheme(
            if (GlobalSettings.enableDarkMode) {
                currentTheme.darkColorScheme
            }
            else {
                currentTheme.lightColorScheme
            }
        ) {
            content()
        }
    }
}