package dev.anthonyhfm.toolcat.modules.app_overview

import androidx.compose.runtime.Composable
import dev.anthonyhfm.toolcat.core.module.ModuleViewModel

object AppOverviewModuleViewModel : ModuleViewModel {
    override val name: String = "Apps"
    override val iconResource: String = "icons/apps.svg"

    @Composable
    override fun ModuleView() {

    }
}
