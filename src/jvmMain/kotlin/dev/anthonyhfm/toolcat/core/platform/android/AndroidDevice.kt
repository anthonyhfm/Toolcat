package dev.anthonyhfm.toolcat.core.platform.android

import dadb.Dadb

data class AndroidDevice(
    val serial: String,
    val emulator: Boolean,
    val adb: Dadb
)
