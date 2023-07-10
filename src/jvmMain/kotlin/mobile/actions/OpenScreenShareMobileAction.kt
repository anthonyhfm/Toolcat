package mobile.actions

import androidx.compose.runtime.Composable
import mobile.MobileDevice

class OpenScreenShareMobileAction : MobileAction {
    override val displayText: String
        get() = "Open Screen Mirroring"

    override fun executeAction(mobileDevice: MobileDevice) {
        Runtime.getRuntime().exec("scrcpy --serial ${mobileDevice.deviceName} --window-title=\"Toolcat Screen Cast (${mobileDevice.deviceName})\"")
    }

    @Composable
    override fun viewContribution() {

    }
}