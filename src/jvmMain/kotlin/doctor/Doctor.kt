package doctor

object Doctor {
    object Found {
        var adb: Boolean = false // Android Debug Bridge
        var scrcpy: Boolean = false // Android Screencopy
        var libimobiledevice: Boolean = false // Mainly primitive iOS Support
        var fbidb: Boolean = false // Advanced iOS Support
    }

    private fun checkContain(command: String, contains: String): Boolean {
        return false
    }

    fun checkCLIDependencies() {

    }
}