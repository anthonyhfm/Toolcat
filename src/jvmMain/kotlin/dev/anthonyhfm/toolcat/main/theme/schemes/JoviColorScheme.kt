package dev.anthonyhfm.toolcat.main.theme.schemes

import androidx.compose.material3.*
import androidx.compose.ui.graphics.Color
import dev.anthonyhfm.toolcat.main.theme.TitleBarColors
import dev.anthonyhfm.toolcat.main.theme.ToolcatColorSchemeSet

class JoviColorScheme : ToolcatColorSchemeSet {
    override val name: String = "Jovi Theme"

    override val lightColorScheme = lightColorScheme(
        background = Color(0xfffff5fe),
        surface = Color(0xfffff5fe),
        primary = Color(0xfff87bff),
        primaryContainer = Color(0xfffccfff),
        secondary = Color(0xffc662cc),
        secondaryContainer = Color(0xffe4cae6)
    )
    override val lightTitleBarColors = TitleBarColors(
        background = Color(0xfffd962cf),
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
