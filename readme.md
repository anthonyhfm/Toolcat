# Toolcat

## Requirements:
### Android Support:
- Android Development CLI Tools (ADB)

### iOS Support:
- libimobiledevice
- ideviceinstaller


## Install necessary tools:

### Mac OS

```shell
# iOS Support
brew install libimobiledevice ideviceinstaller
```

### Windows
1. Install WSL2
2. Install the Debian Distribution
3. Install the Tools with this CLI Command:

```shell
sudo apt-get install \
	build-essential \
	pkg-config \
	checkinstall \
	git \
	autoconf \
	automake \
	libtool-bin \
	libplist-dev \
	libusbmuxd-dev \
	libimobiledevice-glue-dev \
	libssl-dev \
	usbmuxd
```
