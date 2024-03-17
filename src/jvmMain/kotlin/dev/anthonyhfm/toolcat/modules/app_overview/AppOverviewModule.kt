package dev.anthonyhfm.toolcat.modules.app_overview

import androidx.compose.runtime.Composable
import dev.anthonyhfm.toolcat.core.module.ToolcatModule

class AppOverviewModule : ToolcatModule {
    override val name: String = "Apps"
    override val iconResource: String = "icons/apps.svg"
    override val moduleID: String = "toolcat.appoverview"

    @Composable
    override fun ModuleView() {
        AppOverviewView()
    }
}
