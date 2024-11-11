package com.fallenphoenix.timetock

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
        /**
         * A primary capsule-shaped button.
         *
         * It's background color is defined by current [MaterialTheme]
         * and content color by [contentColor]
         *
         * @param onClick The callback function to be executed when the button is clicked.
         * @param label The text to be displayed on the button.
         * @param contentColor The color of the content of the button.
         * [Color.White] by default.
         *
         */
fun PrimaryButton(
    onClick: () -> Unit,
    label: String,
    contentColor: Color = Color.White
) {
    Button(
        colors = ButtonDefaults
            .buttonColors(contentColor = contentColor),
        onClick = onClick,
        modifier = Modifier
            .clip(RoundedCornerShape(50))
            .background(color = MaterialTheme.colorScheme.primary)
    ) {
        Text(
            text = label,
            fontSize = MaterialTheme.typography.titleMedium.fontSize,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Preview
@Composable
fun PrimaryButtonPreview(){
    PrimaryButton(onClick = { println("Primary Button Clicked") }, label = "Primary Button")
}