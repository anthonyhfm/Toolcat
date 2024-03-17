package dev.anthonyhfm.toolcat.modules.more_modules

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.anthonyhfm.toolcat.core.module.ModuleRegistry
import dev.anthonyhfm.toolcat.core.module.ToolcatModule
import dev.anthonyhfm.toolcat.main.theme.Inter
import dev.anthonyhfm.toolcat.main.views.VerticalScrollColumn
import dev.anthonyhfm.toolcat.modules.more_modules.views.ModuleCollection
import dev.anthonyhfm.toolcat.modules.more_modules.views.ModulePreview

class MoreModulesModule : ToolcatModule {
    override val name: String = "More"
    override val iconResource: String = "icons/dataset.svg"
    override val moduleID: String = "toolcat.modules"
    override val showInRegistry: Boolean = false

    @OptIn(ExperimentalLayoutApi::class)
    @Composable
    override fun ModuleView() {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),

                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "More modules",
                    fontFamily = Inter,
                    fontSize = 28.sp,
                )
            }

            VerticalScrollColumn(
                modifier = Modifier.fillMaxWidth()
            ) {
                ModuleCollection(
                    title = "Base"
                ) {
                    FlowRow(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 16.dp, vertical = 16.dp),

                        horizontalArrangement = Arrangement.spacedBy(18.dp, Alignment.CenterHorizontally),
                        verticalArrangement = Arrangement.spacedBy(22.dp)
                    ) {
                        ModuleRegistry.modules.filter { it.moduleID.startsWith("toolcat.") && it.showInRegistry }.forEach {
                            ModulePreview(it.moduleID)
                        }
                    }
                }

                ModuleCollection(
                    title = "Extensions"
                ) {
                    FlowRow(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 16.dp, vertical = 16.dp),

                        horizontalArrangement = Arrangement.spacedBy(18.dp, Alignment.CenterHorizontally),
                        verticalArrangement = Arrangement.spacedBy(22.dp)
                    ) {
                        ModuleRegistry.modules.filter { !it.moduleID.startsWith("toolcat.") }.forEach {
                            ModulePreview(it.moduleID)
                        }
                    }
                }
            }
        }
    }
}
