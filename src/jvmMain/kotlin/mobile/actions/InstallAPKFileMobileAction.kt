package mobile.actions

import com.darkrockstudios.libraries.mpfilepicker.FilePicker
import composeWindow
import mobile.MobileDevice
import java.awt.FileDialog
import java.io.FilenameFilter

class InstallAPKFileMobileAction : MobileAction {
    override val displayText: String
        get() = "Install .APK File"

    override fun executeAction(mobileDevice: MobileDevice) {
        
    }
}