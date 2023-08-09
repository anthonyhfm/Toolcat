package ui.theme.schemes

import androidx.compose.material3.*
import androidx.compose.ui.graphics.Color
import ui.theme.TitleBarColors
import ui.theme.ToolcatColorSchemeSet

class JoviColorScheme : ToolcatColorSchemeSet {
    override val name: String = "Jovi Theme"

    override val lightColorScheme = lightColorScheme(
        primary = Color(0xfff87bff),
        primaryContainer = Color(0xfff87bff)
    )
    override val lightTitleBarColors = TitleBarColors(
        background = Color(0xffa552a9),
        text = Color.White,
        icons = Color.White
    )

    override val darkColorScheme = darkColorScheme(
        background = Color(0xff19141c),
        surface = Color(0xff19141c),
        primary = Color(0xffffbefe),
        primaryContainer = Color(0xffad6cab),
        onPrimary = Color(0xff442743),
        secondary = Color(0xffbe8ebe),
        secondaryContainer = Color(0xff5a435a),
        onSecondary = Color(0xff433243)
    )
    override val darkTitleBarColors = TitleBarColors(
        background = Color(0xff3d2a45),
        text = Color.White,
        icons = Color.White
    )
}