package dev.anthonyhfm.toolcat.modules.screen_cast

import androidx.compose.runtime.Composable
import dev.anthonyhfm.toolcat.core.module.ToolcatModule

class ScreenCastModule : ToolcatModule {
    override val name: String = "Cast"
    override val iconResource: String = "icons/cast_filled.svg"
    override val moduleID: String = "toolcat.screencast"

    @Composable
    override fun ModuleView() {

    }
}
