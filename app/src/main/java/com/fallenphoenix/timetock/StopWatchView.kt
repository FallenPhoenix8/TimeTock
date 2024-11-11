package com.fallenphoenix.timetock

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.delay

@Composable
fun StopWatchView(){
    // Defining state variables
    var milliseconds by remember { mutableStateOf(0) }
    var isRunning by remember { mutableStateOf(false) }
    var isReset by remember { mutableStateOf(false) }

    // Implementing stopwatch functionality,
    // that is, adding 50ms every 50ms until stopped or reset.
    LaunchedEffect(isRunning) {
        if (isReset) {
            milliseconds = 0
        }
        if (isRunning) {
            while (isRunning) {
                milliseconds += 50
                delay(50)
            }
        }
    }

    // StopWatchView GUI
    Column {
        Spacer(modifier = Modifier.fillMaxHeight(0.4f))

        StopWatchClock(milliseconds)

        Spacer(modifier = Modifier.fillMaxHeight(0.5f))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            PrimaryButton(
                label = "Reset",
                onClick = {
                    isRunning = false
                    milliseconds = 0
                    isReset = true
                },
                contentColor = if (milliseconds == 0) Color.White else Color.Red
            )

            PrimaryButton(
                label = if (isRunning) "Stop" else "Start",
                onClick = {
                    isRunning = !isRunning
                    isReset = false
                },
                contentColor = if(isRunning) Color.Red else Color.White
            )
        }
    }


}