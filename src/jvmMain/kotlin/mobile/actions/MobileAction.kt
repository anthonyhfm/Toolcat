package mobile.actions

import androidx.compose.runtime.Composable
import mobile.MobileDevice

interface MobileAction {
    val displayText: String

    fun executeAction(mobileDevice: MobileDevice)

    @Composable
    fun viewContribution()
}