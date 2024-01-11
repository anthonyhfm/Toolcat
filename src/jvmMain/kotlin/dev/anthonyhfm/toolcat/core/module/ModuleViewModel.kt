package dev.anthonyhfm.toolcat.core.module

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter

interface ModuleViewModel {
    val name: String
    val iconResource: String

    @Composable
    fun ModuleView()
}
