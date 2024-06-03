package com.anismhub.ticketsystem.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ConfirmationNumber
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNav(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    data object Home : BottomNav(
        route = "home",
        title = "Home",
        icon = Icons.Rounded.ConfirmationNumber
    )
    data object Notification : BottomNav(
        route = "notification",
        title = "Notification",
        icon = Icons.Rounded.Notifications
    )
    data object Settings : BottomNav(
        route = "settings",
        title = "Settings",
        icon = Icons.Rounded.Settings
    )
}

val BottomNavItem = listOf(
    BottomNav.Home,
    BottomNav.Notification,
    BottomNav.Settings
)
val BottomNavItemRoute = listOf(
    BottomNav.Home.route,
    BottomNav.Notification.route,
    BottomNav.Settings.route
)