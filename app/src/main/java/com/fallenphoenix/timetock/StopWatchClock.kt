package com.fallenphoenix.timetock

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import java.util.Locale



@Composable
        /**
         * A StopWatch clock.
         *
         * This is the main view used to display stopwatch time.
         *
         * @param milliseconds The time in milliseconds.
         */
fun StopWatchClock(milliseconds: Int) {
    val minutes = milliseconds / 60000
    val seconds = (milliseconds % 60000) / 1000
    val hundredths = (milliseconds % 1000) / 10

    // Format the time string
    val formattedTime = String.format(
        Locale.getDefault(),
        "%02d:%02d.%02d",
        minutes,
        seconds,
        hundredths
    )

    // Display the formatted time
    Text(
        text = formattedTime,
        fontSize = MaterialTheme.typography.displayLarge.fontSize,
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth()
    )
}

@Preview
@Composable
fun PreviewStopWatchClock(){
    StopWatchClock(101001)
}