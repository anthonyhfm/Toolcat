package dev.anthonyhfm.toolcat.core.module

import dev.anthonyhfm.toolcat.modules.app_overview.AppOverviewModule
import dev.anthonyhfm.toolcat.modules.device_overview.DeviceOverviewModule
import dev.anthonyhfm.toolcat.modules.more_modules.MoreModulesModule
import dev.anthonyhfm.toolcat.modules.screen_cast.ScreenCastModule
import dev.anthonyhfm.toolcat.modules.toolcat_about.AboutModule
import dev.anthonyhfm.toolcat.modules.toolcat_settings.SettingsModule
import java.io.File

object ModuleRegistry {
    var modules: MutableList<ToolcatModule> = mutableListOf()

    fun loadModules() {
        modules.add(DeviceOverviewModule())
        modules.add(AppOverviewModule())
        modules.add(ScreenCastModule())

        modules.add(MoreModulesModule())
        modules.add(SettingsModule())
        modules.add(AboutModule())

        this.loadJarExtensions()

        // Technicall the Settings and About Page are Modules too, but they are bound to the Sidebar so they are not mentioned here
    }

    private fun loadJarExtensions() {
        val extdir = System.getProperty("user.home") + "/Toolcat/Extensions"

        File(extdir).listFiles()?.filter {
            it.name.endsWith(".jar", ignoreCase = true)
        }?.forEach {
            println("Jar file found: " + it.name)
        }
    }

    fun getModuleById(moduleID: String): ToolcatModule {
        val module = modules.find { it.moduleID == moduleID }

        if (module == null) {
            throw Exception("The Module with the ID $modules was not found. Maybe it was not registered?")
        }

        return module
    }
}