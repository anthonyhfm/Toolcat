package dev.anthonyhfm.toolcat.core.utils

import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.Exception

object Shell {
    fun getResponse(cmd: String): String {
        val process: Process = Runtime.getRuntime().exec(cmd)
        process.waitFor()

        return BufferedReader(
            InputStreamReader(
                process.inputStream
            )
        ).readText()
    }

    fun getResponseLines(cmd: String): Array<String> {
        val process: Process = Runtime.getRuntime().exec(cmd)
        process.waitFor()

        return BufferedReader(
                InputStreamReader(
                    process.inputStream
                )
            )
            .readLines()
            .toTypedArray()
    }

    fun getInputStream(cmd: String): InputStream? {
        try {
            val process = Runtime.getRuntime().exec(cmd)

            return process.inputStream
        } catch (e: Exception) {
            return null
        }
    }
}
