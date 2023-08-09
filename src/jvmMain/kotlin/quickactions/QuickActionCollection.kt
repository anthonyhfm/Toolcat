package quickactions

import quickactions.actions.*

val quickActionList: Array<QuickAction> = arrayOf(
    ToggleWifiAction(),
    ToggleBluetoothAction(),
    ScreenshotAction(),
    OpenDeepLinkAction(),
    ShutdownAction(),
    RestartAction()
)