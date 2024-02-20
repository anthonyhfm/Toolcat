import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
    kotlin("plugin.serialization") version "1.8.0"
}

group = "dev.anthonyhfm"
version = "1.1.0"

repositories {
    google()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

kotlin {
    jvm {
        jvmToolchain(17)
        withJava()
    }
    sourceSets {
        val jvmMain by getting {
            dependencies {
                implementation(compose.desktop.currentOs)
                implementation("org.jetbrains.compose.material:material-icons-core-desktop:1.4.1")
                implementation("org.jetbrains.compose.material3:material3-desktop:1.4.1")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.2")
                implementation("dev.mobile:dadb:1.2.7")

                runtimeOnly("org.jetbrains.compose.material3:material3-desktop:1.4.1")
            }
        }
        val jvmTest by getting
    }
}

compose.desktop {
    application {
        mainClass = "dev.anthonyhfm.toolcat.main.ToolcatWindowKt"
        nativeDistributions {
            targetFormats(TargetFormat.Pkg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "Toolcat"
            packageVersion = version.toString()

            macOS {
                bundleID = "dev.anthonyhfm.toolcat"
                dockName = "Toolcat"

                iconFile.set(project.file("src/jvmMain/resources/desktop-icons/mac-icon.icns"))
            }
            windows {
                shortcut = true
                console = true

                iconFile.set(project.file("src/jvmMain/resources/desktop-icons/windows-icon.ico"))
            }
            linux {
                iconFile.set(project.file("src/jvmMain/resources/desktop-icons/linux-icon.png"))
            }
        }
    }
}
