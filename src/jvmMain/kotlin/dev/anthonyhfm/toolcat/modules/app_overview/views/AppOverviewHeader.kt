package dev.anthonyhfm.toolcat.modules.app_overview.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.anthonyhfm.toolcat.main.theme.Inter

@Composable
fun AppOverviewHeader(
    selected: Int,
    onSelectionChanged: (Int) -> Unit,
    selections: List<String>
) {
    Row(
        modifier = Modifier
            .padding(horizontal = 24.dp)
            .fillMaxWidth()
            .height(60.dp),

        verticalAlignment = Alignment.CenterVertically
    ) {
        if (selections.isNotEmpty()) {
            AppOverviewDeviceSelector(
                selected = selected,
                onSelectionChanged = {
                    onSelectionChanged(it)
                },
                options = selections
            )
        }
    }
}

@Composable
private fun AppOverviewDeviceSelector(
    selected: Int,
    onSelectionChanged: (Int) -> Unit,
    options: List<String>
) {
    var expanded: Boolean by remember { mutableStateOf(false) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),

        modifier = Modifier
            .clip(CircleShape)
            .clickable {
                expanded = true
            }
            .padding(horizontal = 16.dp, vertical = 6.dp)
    ) {
        Icon(
            painter = painterResource("icons/adb.svg"),
            contentDescription = null,

            modifier = Modifier
                .padding(end = 4.dp)
                .clip(CircleShape)
                .size(34.dp)
                .background(MaterialTheme.colorScheme.secondary)
                .padding(6.dp),
            tint = MaterialTheme.colorScheme.onSecondary
        )

        Text(
            text = options[selected],
            fontFamily = Inter,
            fontSize = 24.sp,
        )

        Icon(
            painter = painterResource("icons/chevron_right.svg"),
            contentDescription = null,

            modifier = Modifier
                .rotate(90f)
                .size(28.dp)
        )
    }

    DropdownMenu(
        expanded = expanded,
        onDismissRequest = {
            expanded = false
        },
    ) {
        options.forEachIndexed { index, s ->
            DropdownMenuItem(
                text = {
                    Text(
                        text = s,
                        fontFamily = Inter
                    )
                },
                leadingIcon = {
                    Icon(painterResource("icons/adb.svg"), null)
                },
                onClick = {
                    onSelectionChanged(index)

                    expanded = false
                }
            )
        }
    }
}