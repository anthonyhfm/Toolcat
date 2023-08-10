<div align="center">
  <a>
    <img src="logo.svg">
  </a>
</div>

## Requirements:
### Android Support:
- Android Development CLI Tools (ADB)
- scrcpy

### iOS Support:
- libimobiledevice
- ideviceinstaller


## Install necessary tools:

### Mac OS

```shell
# iOS Support
brew install libimobiledevice ideviceinstaller
# Android Screen Mirroring
brew install scrcpy
```

### Linux

Ubuntu:
```shell
sudo apt-get update
sudo apt-get install usbmuxd libimobiledevice6 libimobiledevice-utils ideviceinstaller
sudo apt-get install scrcpy
```

### Windows
- Install the Android CLI Tools
- Install scrcpy from the [official repository](https://github.com/Genymobile/scrcpy) and put it in the PATH

_iOS Support is currently not given under Windows_
