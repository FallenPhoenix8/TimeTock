package com.fallenphoenix.timetock

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.fallenphoenix.timetock.ui.theme.TimeTockTheme
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TimeTockTheme {
                val navController = rememberNavController()
                var selectedIndex = remember { mutableIntStateOf(0) }
                val navigationBarItems = listOf(
                    NavigationBarItemModel("StopWatch", Icons.AutoMirrored.Filled.ArrowBack) {
                        selectedIndex.value = 0
                        navController.navigate(StopWatchRoute)
                    },
                    NavigationBarItemModel("Timer", Icons.AutoMirrored.Filled.ArrowForward) {
                        selectedIndex.value = 1
                        navController.navigate(TimerRoute)
                    }
                )

                NavHost(
                    navController = navController,
                    startDestination = StopWatchRoute
                ) {
                    composable<StopWatchRoute> {
                        MainScreen(
                            navigationBarItems,
                            selectedIndex = selectedIndex.value
                        ) {
                            StopWatchView()
                        }
                    }

                    composable<TimerClockRoute> {
                        val args = it.toRoute<TimerClockRoute>()
                        MainScreen(
                            navigationBarItems,
                            selectedIndex = selectedIndex.value
                        ) {
                            TimerClock(args.seconds, navController)
                        }
                    }

                    composable<TimerRoute> {
                        MainScreen(
                            navigationBarItems,
                            selectedIndex = selectedIndex.value
                        ) {
                            TimerView(navController)
                        }
                    }

                    composable<StopPlayingRoute> {
                        MainScreen(
                            navigationBarItems,
                            selectedIndex = selectedIndex.value
                        ) {
                            StopPlayingView(navController)
                        }
                    }

                }

            }
        }
    }
}

@Composable
fun MainScreen(
    navigationBarItems: List<NavigationBarItemModel>,
    selectedIndex: Int,
    view: @Composable () -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar {
                navigationBarItems.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = index == selectedIndex,
                        onClick = {
                            item.onClick()
                        },
                        icon = {
                            Icon(item.icon, item.label)
                        },
                        label = {
                            Text(item.label)
                        }
                    )
                }
            }

        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            view()
        }
    }
}


@Serializable
object StopWatchRoute

@Serializable
data class TimerClockRoute(
    val seconds: Int
)

@Serializable
object StopPlayingRoute

@Serializable
object TimerRoute