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
import com.anismhub.ticketsystem.presentation.exportreport.ExportReportScreen
import com.anismhub.ticketsystem.presentation.home.HomeContent
import com.anismhub.ticketsystem.presentation.notification.NotificationContent
import com.anismhub.ticketsystem.presentation.settings.SettingsContent

@Composable
fun HomeNavGraph(
    navController: NavHostController,
    navigateToLogin: () -> Unit,
//    navigateToDetailTicket: () -> Unit,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = BottomNav.Home.route,
        route = Graph.MAIN,
        modifier = modifier
    ) {
        ticketNavGraph(navController = navController)
        composable(route = BottomNav.Home.route) {
            HomeContent(
                navigateToDetailTicket = {
//                    navigateToDetailTicket()
                    navController.navigate(TicketNav.Detail.route)
                })
        }
        composable(route = BottomNav.Notification.route) {
            NotificationContent()
        }
        composable(route = BottomNav.Settings.route) {
            SettingsContent(
                navigateToAuth = { navigateToLogin() },
                navigateToManageAccount = {
                    navController.navigate(TicketNav.ManageAccount.route)
                },
                navigateToExport = {
                    navController.navigate(TicketNav.Export.route)
                }
            )
        }
    }
}

fun NavGraphBuilder.ticketNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.TICKET,
        startDestination = TicketNav.Create.route
    ) {
        composable(route = TicketNav.Detail.route) {
            DetailTicketContent()
        }
        composable(route = TicketNav.Create.route) {
            AddTicketContent()
        }
        composable(route = TicketNav.Export.route) {
            ExportReportScreen()
        }
        composable(route = TicketNav.ManageAccount.route) {
            ExportReportScreen()
        }
    }
}


sealed class TicketNav(val route: String) {
    data object Detail : TicketNav("detail")
    data object Create : TicketNav("create")
    data object Export : TicketNav("export")
    data object ManageAccount : TicketNav("manage_account")
}