package dev.anthonyhfm.toolcat.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import dev.anthonyhfm.toolcat.core.module.ToolcatModule

object Navigation {
    var currentModule: MutableState<String> = mutableStateOf("toolcat.deviceoverview")
        private set

    fun openPage(moduleID: String) {
        currentModule.value = moduleID
    }
}