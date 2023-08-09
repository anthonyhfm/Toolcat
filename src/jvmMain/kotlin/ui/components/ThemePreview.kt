package ui.components

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ui.theme.ToolcatColorSchemeSet
import ui.theme.schemes.StandardColorScheme

@Composable
fun ThemePreview(themeSet: ToolcatColorSchemeSet) {
    Column(
        modifier = Modifier
            .width(160.dp)
            .height(130.dp)
    ) {
        Row(
            modifier = Modifier
                .weight(1F)
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .weight(1F)
                    .fillMaxHeight()
                    .background(themeSet.lightColorScheme.background)
            ) {
                Box(Modifier.height(12.dp).fillMaxWidth().background(themeSet.lightTitleBarColors.background))
                Spacer(Modifier.height(10.dp))

                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.End,
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    items(3) {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(topStart = 5.dp, bottomStart = 5.dp))
                                .fillMaxWidth(0.8f)
                                .height(10.dp)
                                .background(themeSet.lightColorScheme.primary.copy(alpha = 0.2F))
                        ) {
                            Box(
                                modifier = Modifier
                                    .padding(3.dp)
                                    .clip(CircleShape)
                                    .size(4.dp)
                                    .background(themeSet.lightColorScheme.primary)
                            )
                        }
                    }
                }

                Row(
                    modifier = Modifier
                        .padding(top = 14.dp)
                        .fillMaxWidth()
                        .height(32.dp),

                    horizontalArrangement = Arrangement.End
                ) {
                    Icon(
                        painter = painterResource("icons/toolcat-logo.png"),
                        contentDescription = "Light Toolcat Logo",
                        modifier = Modifier
                            .height(32.dp)
                            .offset(x = 16.dp),
                        tint = themeSet.lightColorScheme.primary
                    )
                }
            }

            Column(
                modifier = Modifier
                    .weight(1F)
                    .fillMaxHeight()
                    .background(themeSet.darkColorScheme.background)
            ) {
                Box(Modifier.height(12.dp).fillMaxWidth().background(themeSet.darkTitleBarColors.background))
                Spacer(Modifier.height(10.dp))

                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    items(3) {
                        Box (
                            modifier = Modifier
                                .clip(RoundedCornerShape(topEnd = 5.dp, bottomEnd = 5.dp))
                                .fillMaxWidth(0.8f)
                                .height(10.dp)
                                .background(themeSet.darkColorScheme.primary.copy(alpha = 0.2F))
                        ) {
                            Box(
                                Modifier
                                    .align(Alignment.TopEnd)
                                    .padding(3.dp)
                                    .clip(CircleShape)
                                    .fillMaxHeight()
                                    .width(16.dp)
                                    .background(themeSet.darkColorScheme.background)
                            )
                        }
                    }
                }

                Row(
                    modifier = Modifier
                        .padding(top = 14.dp)
                        .fillMaxWidth()
                        .height(32.dp),
                ) {
                    Icon(
                        painter = painterResource("icons/toolcat-logo.png"),
                        contentDescription = "Light Toolcat Logo",
                        modifier = Modifier
                            .height(32.dp)
                            .offset(x = -16.dp)
                            .drawWithContent {
                                clipRect(left = size.width / 2f) {
                                    this@drawWithContent.drawContent()
                                }
                            },
                        tint = themeSet.darkColorScheme.primary
                    )
                }

                Row(
                    modifier = Modifier
                        .padding(top = 4.dp, end = 4.dp)
                        .fillMaxWidth()
                        .height(16.dp),

                    horizontalArrangement = Arrangement.End
                ) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(4.dp))
                            .size(16.dp)
                            .background(themeSet.darkColorScheme.primaryContainer)
                    )
                }
            }
        }
    }
}