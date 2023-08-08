package quickactions

import androidx.compose.runtime.Composable
import mobile.MobileDevice

enum class QuickActionSize {
    SMALL,
    LARGE
}

enum class QuickActionAvailability {
    ANDROID, IOS, ANDROID_SIM, IOS_SIM
}

interface QuickAction {
    val actionSize: QuickActionSize
    val availability: List<QuickActionAvailability>

    @Composable
    fun content(mobileDevice: MobileDevice)
}