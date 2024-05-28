package com.anismhub.ticketsystem.navigation.graph

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.anismhub.ticketsystem.navigation.BottomNav
import com.anismhub.ticketsystem.presentation.addticket.AddTicketContent
import com.anismhub.ticketsystem.presentation.home.HomeContent
import com.anismhub.ticketsystem.presentation.home.HomeScreen
import com.anismhub.ticketsystem.presentation.notification.NotificationContent
import com.anismhub.ticketsystem.presentation.settings.SettingsContent

@Composable
fun HomeNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = BottomNav.Home.route,
        route = Graph.MAIN,
        modifier = modifier
    ) {
        composable(route = BottomNav.Home.route) {
            HomeContent()
        }
        composable(route = BottomNav.Notification.route) {
            NotificationContent()
        }
        composable(route = BottomNav.Settings.route) {
            SettingsContent()
        }

    }
}