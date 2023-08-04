package ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ui.Inter

@Composable
fun AboutView() {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),

            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource("icons/toolcat-logo.png"),
                contentDescription = null,
                modifier = Modifier
                    .height(180.dp)
            )

            Spacer(Modifier.height(16.dp))

            Text(
                text = "Toolcat",
                fontSize = 32.sp,
                fontWeight = FontWeight.Light,
                letterSpacing = 4.sp,
                fontFamily = Inter,
                color = MaterialTheme.colorScheme.onBackground
            )

            Text(
                text = "Made by Anthony Hofmeister",
                fontWeight = FontWeight.Light,
                fontFamily = Inter,
                color = MaterialTheme.colorScheme.onBackground
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .align(Alignment.BottomCenter),

            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Version 1.0.0",
                fontWeight = FontWeight.Light,
                fontFamily = Inter,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}