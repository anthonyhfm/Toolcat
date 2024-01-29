package dev.anthonyhfm.toolcat.modules.device_overview.actions.android

import dev.anthonyhfm.toolcat.core.platform.android.AndroidDevice
import dev.anthonyhfm.toolcat.modules.device_overview.actions.QuickActionModel

internal fun getQuickActions(androidDevice: AndroidDevice): List<QuickActionModel<AndroidDevice>> {
    return listOf(
        /*OpenDeepLinkAction(androidDevice),
        CaffeinateAction(androidDevice),*/
        ScreenshotAction(androidDevice),
        ForceRebootAction(androidDevice),
        NavigationSimulatorAction(androidDevice)
    )
}
