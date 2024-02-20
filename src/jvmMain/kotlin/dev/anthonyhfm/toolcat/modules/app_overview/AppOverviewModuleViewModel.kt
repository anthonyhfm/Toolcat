package dev.anthonyhfm.toolcat.modules.app_overview

import androidx.compose.runtime.Composable
import dev.anthonyhfm.toolcat.core.module.ToolcatModule

object AppOverviewModuleViewModel : ToolcatModule {
    override val name: String = "Apps"
    override val iconResource: String = "icons/apps.svg"

    @Composable
    override fun ModuleView() {
        AppOverviewView()
    }
}
