package com.fallenphoenix.timetock


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.fallenphoenix.timetock.ui.theme.TimeTockTheme
import kotlinx.coroutines.delay
import java.util.Locale

/**
 * Formats remaining seconds into a string.
 * The string is of the format:
 * HH:MM:SS
 *
 * @param seconds The remaining seconds.
 */
fun getFormattedTimeRemaining(seconds: Int): String {
    val hours = seconds / 3600
    val minutes = (seconds % 3600) / 60
    val remainingSeconds = seconds % 60

    return String.format(Locale.getDefault(), "%02d:%02d:%02d", hours, minutes, remainingSeconds)
}

@Composable
fun TimerClock(seconds: Int, navHostController: NavHostController) {
    var isRunning by remember { mutableStateOf(true) }
    var remainingSeconds by remember { mutableStateOf(seconds) }

    // Timer logic
    LaunchedEffect(isRunning) {
        while (isRunning && remainingSeconds > 0) {
            remainingSeconds -= 1
            delay(1000)
            if (remainingSeconds == 0) {
                navHostController.navigate(StopPlayingRoute)
            }
        }

    }

    // Display remaining time
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = getFormattedTimeRemaining(remainingSeconds),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.displayLarge
        )
        Box {
            CircularProgressIndicator(
                progress = { 1f },
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier
                    .fillMaxWidth(0.5f),
                strokeWidth = 10.dp
            )

            CircularProgressIndicator(
                progress = { remainingSeconds.toFloat() / seconds.toFloat() },
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .zIndex(1F),
                strokeWidth = 10.dp

            )

        }

        // Row containing "Delete" and "Resume" / "Pause" buttons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            PrimaryButton(
                label = "Delete",
                onClick = {
                    navHostController.navigate(TimerRoute)
                }
            )

            PrimaryButton(
                label = if (isRunning) "Pause" else "Resume",
                onClick = {
                    isRunning = !isRunning
                },
                contentColor = if (isRunning) Color.Red else Color.White
            )
        }



    }

}


@Preview
@Composable
fun PreviewTimerClock() {
    TimeTockTheme {
        TimerClock(seconds = 10, rememberNavController())
    }

}


