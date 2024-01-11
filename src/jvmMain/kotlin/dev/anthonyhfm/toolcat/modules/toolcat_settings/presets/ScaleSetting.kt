package dev.anthonyhfm.toolcat.modules.toolcat_settings.presets

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState

class ScaleSetting(
    val displayText: String,
    val value: MutableState<Int>,
    val minValue: Int,
    val maxValue: Int,
    val onValueChange: (Int) -> Unit
) : SettingModel {
    @Composable
    override fun Setting() {

    }
}
