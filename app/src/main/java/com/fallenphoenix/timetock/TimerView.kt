package com.fallenphoenix.timetock

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController


@Composable
fun TimerView(navHostController: NavHostController) {
    // Defining state variables
    val viewModel = remember { TimeModel() }

    // TimerView GUI
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Spacer(modifier = Modifier.height(10.dp))

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

        PrimaryButton(onClick = {
            if(viewModel.getFullTimeSeconds() > 0) {
                navHostController.navigate(TimerClockRoute(seconds = viewModel.getFullTimeSeconds()))
            }
        }, "Start")

    }


}

@Preview
@Composable
fun PreviewTimerView() {
    TimerView(navHostController = rememberNavController())
}