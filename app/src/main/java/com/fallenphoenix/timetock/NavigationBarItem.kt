package com.fallenphoenix.timetock

import androidx.compose.ui.graphics.vector.ImageVector

data class NavigationBarItemModel(
    val label: String,
    val icon: ImageVector,
    val onClick: () -> Unit
)


