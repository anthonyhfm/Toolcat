package mobile.actions

import mobile.MobileDevice

interface MobileAction {
    val displayText: String

    fun executeAction(mobileDevice: MobileDevice)
}