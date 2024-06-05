package com.anismhub.ticketsystem.navigation

import androidx.annotation.DrawableRes
import com.anismhub.ticketsystem.R

sealed class BottomNav(
    val route: String,
    val title: String,
    @DrawableRes
    val icon: Int
) {
    data object Home : BottomNav(
        route = "home",
        title = "Tiket",
        icon = R.drawable.confirmation_number_24px
    )
    data object Notification : BottomNav(
        route = "notification",
        title = "Notifikasi",
        icon = R.drawable.notifications_24px
    )
    data object Settings : BottomNav(
        route = "settings",
        title = "Pengaturan",
        icon = R.drawable.settings_24px
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