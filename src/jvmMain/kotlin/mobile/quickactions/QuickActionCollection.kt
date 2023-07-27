package mobile.quickactions

import mobile.quickactions.actions.OpenDeepLinkAction
import mobile.quickactions.actions.ScreenshotAction
import mobile.quickactions.actions.ToggleBluetoothAction
import mobile.quickactions.actions.ToggleWifiAction

val quickActionList: Array<QuickAction> = arrayOf(
    ToggleWifiAction(),
    ToggleBluetoothAction(),
    ScreenshotAction(),
    OpenDeepLinkAction()
)