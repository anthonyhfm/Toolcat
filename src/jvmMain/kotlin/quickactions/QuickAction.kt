package quickactions

import androidx.compose.runtime.Composable
import mobile.MobileDevice

enum class QuickActionSize {
    SMALL,
    LARGE
}

enum class QuickActionAvailability {
    ANDROID, IOS, BOTH
}

interface QuickAction {
    val actionSize: QuickActionSize
    val availability: QuickActionAvailability

    @Composable
    fun content(mobileDevice: MobileDevice)
}