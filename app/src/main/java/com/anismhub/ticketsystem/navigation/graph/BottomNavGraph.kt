package com.anismhub.ticketsystem.navigation.graph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.anismhub.ticketsystem.navigation.BottomNav
import com.anismhub.ticketsystem.navigation.TicketNav
import com.anismhub.ticketsystem.presentation.screen.notification.NotificationScreen
import com.anismhub.ticketsystem.presentation.screen.settings.SettingsScreen
import com.anismhub.ticketsystem.presentation.screen.tickets.TicketScreen

@Composable
fun BottomNavGraph(
    onTitleChange: (String) -> Unit,
    navController: NavHostController,
    navigateToLogin: () -> Unit,
) {
    NavHost(
        navController = navController,
        startDestination = BottomNav.Home.route,
        route = Graph.MAIN,
    ) {
        composable(route = BottomNav.Home.route) {
            TicketScreen(
                navigateToDetailTicket = { title, ticketId ->
                    navController.navigate(TicketNav.Detail.createRoute(ticketId))
                    onTitleChange(title)
                })
        }
        composable(route = BottomNav.Notification.route) {
            NotificationScreen(
                navigateToDetail = { title, ticketId ->
                    navController.navigate(TicketNav.Detail.createRoute(ticketId))
                    onTitleChange(title)
                }
            )
        }
        composable(route = BottomNav.Settings.route) {
            SettingsScreen(
                navigateToAuth = { navigateToLogin() },
                navigateToManageAccount = { title ->
                    navController.navigate(TicketNav.ManageAccount.route)
                    onTitleChange(title)
                },
                navigateToExport = { title ->
                    navController.navigate(TicketNav.Export.route)
                    onTitleChange(title)
                }
            )
        }
        ticketNavGraph(
            onTitleChange = { onTitleChange(it) },
            navController = navController
        )
    }
}



