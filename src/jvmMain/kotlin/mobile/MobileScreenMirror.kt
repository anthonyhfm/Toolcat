package mobile

fun MobileDevice.openScreenMirror() {
    when (this.deviceType) {
        DeviceType.ANDROID -> {
            Runtime.getRuntime().exec("scrcpy --serial ${this.serial} --window-title=\"Toolcat Screen Mirror (${this.serial})\"")
        }

        DeviceType.IOS -> return
    }
}