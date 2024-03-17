package dev.anthonyhfm.toolcat.core.module

import androidx.compose.runtime.Composable

interface ToolcatModule {
    val name: String
    val iconResource: String
    val moduleID: String
    val showInRegistry: Boolean
        get() = true

    @Composable
    fun ModuleView()
}
