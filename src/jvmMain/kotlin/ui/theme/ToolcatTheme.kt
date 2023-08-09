package ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import settings.GlobalSettings
import ui.theme.schemes.StandardColorScheme

object ToolcatTheme {
    val themeCollection: List<ToolcatColorSchemeSet> = listOf(
        StandardColorScheme()
    )

    val currentThemeSet: ToolcatColorSchemeSet = themeCollection[0]

    @Composable
    fun runCompose(content: @Composable () -> Unit) {
        MaterialTheme(
            if (GlobalSettings.enableDarkMode) {
                currentThemeSet.darkColorScheme
            }
            else {
                currentThemeSet.lightColorScheme
            }
        ) {
            content()
        }
    }
}