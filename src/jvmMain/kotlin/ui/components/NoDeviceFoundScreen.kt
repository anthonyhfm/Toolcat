package ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NoDeviceFound() {
    Column(
        modifier = Modifier
            .fillMaxSize(),

        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource("icons/mobile_off.svg"),
            contentDescription = "No Mobile",
            modifier = Modifier
                .size(96.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "No devices found.",
            fontWeight = FontWeight.SemiBold,
            fontSize = 28.sp
        )

        Spacer(modifier = Modifier.height(36.dp))

        Row(
            Modifier.fillMaxWidth(0.75F)
        ) {
            Text(
                text = "It seems like there are no devices connected. If there are but they dont get displayed try reconnecting it. If that still does not work, try adjusting Toolcat or Device Settings (Developer Mode)",
                textAlign = TextAlign.Center
            )
        }
    }
}