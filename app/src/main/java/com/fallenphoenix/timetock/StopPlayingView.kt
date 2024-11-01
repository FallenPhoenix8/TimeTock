package com.fallenphoenix.timetock

import android.media.MediaPlayer
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fallenphoenix.timetock.ui.theme.TimeTockTheme

@Composable
fun StopPlayingView() {
    val media = MediaPlayer.create(LocalContext.current, R.raw.alarm)
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
          },
            shape = CircleShape) {
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
        StopPlayingView()
    }


}