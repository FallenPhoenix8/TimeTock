package com.fallenphoenix.timetock

import android.media.MediaPlayer
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.fallenphoenix.timetock.ui.theme.TimeTockTheme

@Composable
fun StopPlayingView(navHostController: NavHostController) {
    val context = LocalContext.current
    val media = remember { MediaPlayer.create(context, R.raw.alarm) }
    media.start()
    media.isLooping = true

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = {
                media.stop()
                navHostController.navigate(TimerRoute)
            },
            shape = CircleShape
        ) {
            Icon(
                Icons.Default.Close,
                contentDescription = "Close",
                tint = Color.Black,
            )
        }
    }
}

@Preview
@Composable
fun PreviewStopPlayingView() {
    TimeTockTheme {
        StopPlayingView(navHostController = rememberNavController())
    }


}