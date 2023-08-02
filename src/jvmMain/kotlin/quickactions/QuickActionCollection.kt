package quickactions

import quickactions.actions.OpenDeepLinkAction
import quickactions.actions.ScreenshotAction
import quickactions.actions.ToggleBluetoothAction
import quickactions.actions.ToggleWifiAction

val quickActionList: Array<QuickAction> = arrayOf(
    ToggleWifiAction(),
    ToggleBluetoothAction(),
    ScreenshotAction(),
    OpenDeepLinkAction()
)