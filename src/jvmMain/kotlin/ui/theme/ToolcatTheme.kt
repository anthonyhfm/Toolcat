package ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import settings.GlobalSettings
import ui.theme.schemes.JoviColorScheme
import ui.theme.schemes.StandardColorScheme

object ToolcatTheme {
    val themeCollection: List<ToolcatColorSchemeSet> = listOf(
        StandardColorScheme(),
        JoviColorScheme()
    )

    @Composable
    fun runCompose(content: @Composable () -> Unit) {
        val currentThemeSet: ToolcatColorSchemeSet = themeCollection[GlobalSettings.selectedTheme.value]

        MaterialTheme(
            if (GlobalSettings.enableDarkMode.value) {
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