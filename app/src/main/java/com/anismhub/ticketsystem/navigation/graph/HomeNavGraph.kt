package com.anismhub.ticketsystem.navigation.graph

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.anismhub.ticketsystem.navigation.BottomNav
import com.anismhub.ticketsystem.presentation.addticket.AddTicketContent
import com.anismhub.ticketsystem.presentation.detailticket.DetailTicketContent
import com.anismhub.ticketsystem.presentation.detailticket.DetailTicketScreen
import com.anismhub.ticketsystem.presentation.home.HomeContent
import com.anismhub.ticketsystem.presentation.notification.NotificationContent
import com.anismhub.ticketsystem.presentation.settings.SettingsContent

@Composable
fun HomeNavGraph(
    navController: NavHostController,
    navigateToLogin: () -> Unit,
    navigateToDetailTicket: () -> Unit,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = BottomNav.Home.route,
        route = Graph.MAIN,
        modifier = modifier
    ) {
        composable(route = BottomNav.Home.route) {
            HomeContent(navigateToDetailTicket = { navigateToDetailTicket() })
        }
        composable(route = BottomNav.Notification.route) {
            NotificationContent()
        }
        composable(route = BottomNav.Settings.route) {
            SettingsContent(
                navigateToLogin = { navigateToLogin() }
            )
        }
    }
}

fun NavGraphBuilder.ticketNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.TICKET,
        startDestination = TicketScreen.Create.route
    ) {
        composable(route = TicketScreen.Detail.route) {
            DetailTicketContent()
        }
        composable(route = TicketScreen.Create.route) {
            AddTicketContent()
        }

    }
}

sealed class TicketScreen(val route: String) {
    data object Detail : TicketScreen("detail")
    data object Create : TicketScreen("create")
}