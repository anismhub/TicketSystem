package com.anismhub.ticketsystem.navigation

sealed class BottomNav(
    val route: String,
    val title: String
) {
    data object Home : BottomNav(
        route = "home",
        title = "Home"
    )
    data object Notification : BottomNav(
        route = "notification",
        title = "Notification"
    )
    data object Settings : BottomNav(
        route = "settings",
        title = "Settings"
    )
}

val BottomNavItem = listOf(
    BottomNav.Home,
    BottomNav.Notification,
    BottomNav.Settings
)