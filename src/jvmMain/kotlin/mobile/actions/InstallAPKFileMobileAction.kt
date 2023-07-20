package mobile.actions

import androidx.compose.runtime.Composable
import composeWindow
import mobile.MobileDevice
import java.awt.FileDialog
import java.io.FilenameFilter

class InstallAPKFileMobileAction : MobileAction {
    override val displayText: String
        get() = "Install .APK File"

    override fun executeAction(mobileDevice: MobileDevice) {
        print("")
    }

    @Composable
    override fun viewContribution() {
        TODO("Not yet implemented")
    }
}