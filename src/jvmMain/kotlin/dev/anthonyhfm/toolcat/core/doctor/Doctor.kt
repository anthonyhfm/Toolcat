package dev.anthonyhfm.toolcat.core.doctor

import dev.anthonyhfm.toolcat.core.utils.Shell

data class DoctorDependency(
    val name: String,
    val purpose: String,
    val instructions: String,
    val command: String,
    val expectedResponse: Regex
)

data class DoctorResponse(
    val dependency: DoctorDependency,
    val available: Boolean,
)

private val doctorDependencies = arrayOf(
    DoctorDependency(
        name = "Screen Copy",
        purpose = "This Application is used for screen mirroring Android devices. Please install it to your PATH so Toolcat can pick it up.",
        instructions = "https://github.com/Genymobile/scrcpy?tab=readme-ov-file#get-the-app",
        command = "scrcpy --version",
        expectedResponse = Regex("""scrcpy \d+(\.\d+)*\s+<https://github\.com/Genymobile/scrcpy>""")
    )
)

object Doctor {
    fun checkDependencies(): Array<DoctorResponse> {
        var responseArray: Array<DoctorResponse> = arrayOf()

        doctorDependencies.forEach {
            val response = Shell.getResponse(it.command)

            responseArray = responseArray.plus(
                DoctorResponse(
                    dependency = it,
                    available = it.expectedResponse.find(response) != null
                )
            )
        }

        return responseArray
    }
}