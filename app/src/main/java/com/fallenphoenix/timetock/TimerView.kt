package com.fallenphoenix.timetock

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun TimerView() {
    // Defining state variables
    val viewModel = remember { TimeModel() }
//    val seconds by viewModel.seconds.collectAsState()
//    val minutes by viewModel.minutes.collectAsState()
//    val hours by viewModel.hours.collectAsState()

    // TimerView GUI
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
        ) {
            // Hours
            TimeSlider(timeUnit = "Hours", changeHandler = viewModel::onHoursChanged)
            Column {
                Text("")
                Text(
                    text = " : ",
                    fontSize = MaterialTheme.typography.displayLarge.fontSize,
                    fontWeight = FontWeight.SemiBold
                )
            }

            // Minutes
            TimeSlider(timeUnit = "Minutes", changeHandler = viewModel::onMinutesChanged)
            Column {
                Text("")
                Text(
                    text = " : ",
                    fontSize = MaterialTheme.typography.displayLarge.fontSize,
                    fontWeight = FontWeight.SemiBold
                )
            }
            // Seconds
            TimeSlider(timeUnit = "Seconds", changeHandler = viewModel::onSecondsChanged)
        }

    }


}

@Preview
@Composable
fun PreviewTimerView() {
    TimerView()
}