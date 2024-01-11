package dev.anthonyhfm.toolcat.main.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.ui.graphics.Color

data class TitleBarColors(
    val background: Color,
    val text: Color,
    val icons: Color
)

interface ToolcatColorSchemeSet {
    val name: String
    val lightColorScheme: ColorScheme
    val lightTitleBarColors: TitleBarColors
    val darkColorScheme: ColorScheme
    val darkTitleBarColors: TitleBarColors
}
