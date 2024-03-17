package dev.anthonyhfm.toolcat.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import dev.anthonyhfm.toolcat.core.module.ModuleRegistry
import dev.anthonyhfm.toolcat.core.module.ToolcatModule
import dev.anthonyhfm.toolcat.main.theme.Inter
import dev.anthonyhfm.toolcat.modules.more_modules.MoreModulesModule
import dev.anthonyhfm.toolcat.modules.toolcat_about.AboutModule
import dev.anthonyhfm.toolcat.modules.toolcat_settings.SettingsModule
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.*
import java.util.*
import java.util.prefs.Preferences
import kotlin.jvm.internal.Reflection

object Sidebar {
    private val essentialModules: List<ToolcatModule> = listOf(SettingsModule(), AboutModule())
    var pinnedModules: MutableState<List<String>> = mutableStateOf(listOf())

    init {
        val pins = Preferences.userRoot().get("pinnedModules", null)

        if (pins == null) {
            pinnedModules.value = listOf(
                "toolcat.deviceoverview",
                "toolcat.appoverview",
                "toolcat.screencast"
            )

            Preferences.userRoot().put("pinnedModules", Json.encodeToString(pinnedModules.value))
        } else {
            Json.decodeFromString<List<String>>(pins).forEach {
                pinnedModules.value = pinnedModules.value.plus(it)
            }
        }
    }

    fun pin(moduleID: String) {
        pinnedModules.value = pinnedModules.value.plus(moduleID)

        Preferences.userRoot().put("pinnedModules", Json.encodeToString(pinnedModules.value))
    }

    fun unpin(moduleID: String) {
        pinnedModules.value = pinnedModules.value.minus(moduleID)

        Preferences.userRoot().put("pinnedModules", Json.encodeToString(pinnedModules.value))
    }

    fun isPinned(moduleID: String): Boolean {
        return pinnedModules.value.contains(moduleID)
    }

    @Composable
    fun View() {
        val moreModulesModule: ToolcatModule by remember { mutableStateOf(MoreModulesModule()) }
        var pinned: List<ToolcatModule> by remember { mutableStateOf(listOf()) }
        var unpinnedModule: ToolcatModule? by remember { mutableStateOf(null) }

        LaunchedEffect(pinnedModules.value) {
            pinned = listOf()

            pinnedModules.value.forEach {
                pinned = pinned.plus(ModuleRegistry.getModuleById(it))
            }
        }

        LaunchedEffect(Navigation.currentModule.value) {
            if (!pinnedModules.value.contains(Navigation.currentModule.value)) {
                val module = ModuleRegistry.getModuleById(Navigation.currentModule.value)

                if (module.showInRegistry) {
                    unpinnedModule = module
                } else {
                    unpinnedModule = null
                }
            } else {
                unpinnedModule = null
            }
        }

        NavigationRail {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
            ) {
                pinned.forEach {
                    NavigationRailItem(
                        icon = { Icon(painterResource(it.iconResource), null) },
                        label = { Text(it.name, fontFamily = Inter) },
                        selected = Navigation.currentModule.value == it.moduleID,
                        onClick = {
                            Navigation.openPage(it.moduleID)
                        }
                    )
                }

                if(unpinnedModule != null) {
                    Divider(Modifier.width(40.dp).padding(vertical = 8.dp).align(Alignment.CenterHorizontally))

                    NavigationRailItem(
                        icon = { Icon(painterResource(unpinnedModule!!.iconResource), null) },
                        label = { Text(unpinnedModule!!.name, fontFamily = Inter) },
                        selected = Navigation.currentModule.value == unpinnedModule!!.moduleID,
                        onClick = {
                            Navigation.openPage(unpinnedModule!!.moduleID)
                        }
                    )
                }

                Divider(Modifier.width(40.dp).padding(vertical = 8.dp).align(Alignment.CenterHorizontally))

                NavigationRailItem(
                    icon = { Icon(painterResource(moreModulesModule.iconResource), null) },
                    label = { Text(moreModulesModule.name, fontFamily = Inter) },
                    selected = Navigation.currentModule.value == moreModulesModule.moduleID,
                    onClick = {
                        Navigation.openPage(moreModulesModule.moduleID)
                    }
                )
            }

            essentialModules.forEach {
                NavigationRailItem(
                    icon = { Icon(painterResource(it.iconResource), null) },
                    label = { Text(it.name, fontFamily = Inter) },
                    selected = Navigation.currentModule.value == it.moduleID,
                    onClick = {
                        Navigation.openPage(it.moduleID)
                    }
                )
            }
        }
    }
}