package dev.anthonyhfm.toolcat.core.module

import androidx.compose.runtime.Composable

interface ToolcatModule {
    val name: String
    val iconResource: String

    @Composable
    fun ModuleView()
}
