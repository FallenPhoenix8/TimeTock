package com.fallenphoenix.timetock


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import java.util.Locale

@Composable
fun TimeSlider(timeUnit: String, changeHandler: (String) -> Unit) {
    var itemsArr: Array<String> = emptyArray()
    var activeItem by remember { mutableStateOf("00") }

    // Creating state variables that will be used to manage our list
    val listState = remember { LazyListState() }
    val coroutineScope = rememberCoroutineScope()

    // Scroll to the first visible item when the component is first composed
    LaunchedEffect(Unit) {
        coroutineScope.launch {
            // Check if there are any visible items
            val firstVisibleItemIndex = listState.firstVisibleItemIndex
            if (firstVisibleItemIndex >= 0) {
                listState.animateScrollToItem(firstVisibleItemIndex)
            }
        }
    }
    // Generating an array of strings from 00 to 59
    for (i in 0..59) {
        val newItem = String.format(Locale.getDefault(), "%02d", i)
        itemsArr = itemsArr.plus(newItem)
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = timeUnit,
            fontStyle = MaterialTheme.typography.displaySmall.fontStyle,
            color = Color.Gray
        )
        LazyColumn(
            modifier = Modifier.height(75.dp),
            state = listState
        ) {
            items(itemsArr.size) { item ->
                Text(
                    text = itemsArr[item],
                    fontSize = MaterialTheme.typography.displayLarge.fontSize,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(vertical = 5.dp)
                )
            }
        }
        // Updating the active item when the list is scrolled
        LaunchedEffect(listState) {
            snapshotFlow {
                listState.firstVisibleItemIndex
            }.collect { index ->
                activeItem = itemsArr[index]
                changeHandler(activeItem)
            }
        }
        // Detect scroll end and scroll to the first visible item
        LaunchedEffect(Unit) {
            snapshotFlow { listState.isScrollInProgress }
                .collect { isScrolling ->
                    if (!isScrolling) {
                        val firstVisibleItemIndex = listState.firstVisibleItemIndex
                        coroutineScope.launch {
                            listState.animateScrollToItem(firstVisibleItemIndex)
                        }
                    }
                }
        }
    }




}

fun previewChangeHandler(newValue: String) {
    println(newValue)
}

@Preview
@Composable
fun TimeSliderPreview() {
    TimeSlider(timeUnit = "Hours", changeHandler = ::previewChangeHandler)
}
