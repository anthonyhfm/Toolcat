import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
}

group = "dev.anthonyhfm"
version = "1.0-SNAPSHOT"

repositories {
    google()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

kotlin {
    jvm {
        jvmToolchain(11)
        withJava()
    }
    sourceSets {
        val jvmMain by getting {
            dependencies {
                implementation(compose.desktop.currentOs)
                implementation("org.jetbrains.compose.material:material-icons-core-desktop:1.4.1")
                implementation("org.jetbrains.compose.material3:material3-desktop:1.4.1")

                // Reserved for upcoming feature
                // implementation("com.github.serezhka:java-airplay-lib:1.0.5")

                runtimeOnly("org.jetbrains.compose.material3:material3-desktop:1.4.1")
            }
        }
        val jvmTest by getting
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Exe, TargetFormat.AppImage)
            packageName = "Toolcat"
            packageVersion = "1.0.0"

            macOS {
                bundleID = "dev.anthonyhfm.toolcat"

                iconFile.set(project.file("src/jvmMain/resources/desktop-icons/mac-icon.icns"))
            }
            windows {
                iconFile.set(project.file("src/jvmMain/resources/desktop-icons/windows-icon.ico"))
            }
            linux {
                iconFile.set(project.file("src/jvmMain/resources/desktop-icons/linux-icon.png"))

                menuGroup = "Development"
            }
        }
    }
}
