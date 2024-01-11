package dev.anthonyhfm.toolcat.modules.toolcat_settings.presets

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState

class MultiselectSetting(
    val displayText: String,
    val selections: List<String>,
    val value: MutableState<Int>,
    val onValueChange: (Int) -> Unit
) : SettingModel {
    @Composable
    override fun Setting() {

    }
}
