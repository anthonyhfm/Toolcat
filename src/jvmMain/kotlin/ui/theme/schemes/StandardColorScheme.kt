package ui.theme.schemes

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color
import ui.theme.ToolcatColorSchemeSet
import ui.theme.TitleBarColors

class StandardColorScheme : ToolcatColorSchemeSet {
    override val name: String = "Standard Theme"

    override val lightColorScheme = lightColorScheme()
    override val lightTitleBarColors = TitleBarColors(
        background = Color(0xff6750a4),
        text = Color.White,
        icons = Color.White
    )

    override val darkColorScheme = darkColorScheme()
    override val darkTitleBarColors = TitleBarColors(
        background = Color(0xff34333a),
        text = Color.White,
        icons = Color.White
    )
}