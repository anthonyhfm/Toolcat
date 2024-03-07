package dev.anthonyhfm.toolcat.modules.screen_cast

import androidx.compose.runtime.Composable
import dev.anthonyhfm.toolcat.core.module.ToolcatModule

object ScreenCastModuleViewModel : ToolcatModule {
    override val name: String = "Cast"
    override val iconResource: String = "icons/cast_filled.svg"

    @Composable
    override fun ModuleView() {

    }
}
