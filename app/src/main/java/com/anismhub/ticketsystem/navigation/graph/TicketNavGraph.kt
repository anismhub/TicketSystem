package com.anismhub.ticketsystem.navigation.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.anismhub.ticketsystem.navigation.TicketNav
import com.anismhub.ticketsystem.presentation.screen.settings.accounts.AccountManageScreen
import com.anismhub.ticketsystem.presentation.screen.settings.accounts.create.AccountsCreateScreen
import com.anismhub.ticketsystem.presentation.screen.settings.accounts.update.AccountsUpdateScreen
import com.anismhub.ticketsystem.presentation.screen.settings.exportreport.ExportReportScreen
import com.anismhub.ticketsystem.presentation.screen.tickets.addticket.AddTicketScreen
import com.anismhub.ticketsystem.presentation.screen.tickets.detailticket.DetailTicketScreen


fun NavGraphBuilder.ticketNavGraph(
    onTitleChange: (String) -> Unit,
    navController: NavHostController
) {
    navigation(
        route = Graph.TICKET,
        startDestination = TicketNav.Detail.route
    ) {
        composable(
            route = TicketNav.Detail.route,
            arguments = listOf(navArgument("ticketId") { type = NavType.IntType })
        ) {
            val ticketId = it.arguments?.getInt("ticketId") ?: -1
            DetailTicketScreen(ticketId = ticketId, onNavUp = { navController.navigateUp() })
        }
        composable(route = TicketNav.Create.route) {
            AddTicketScreen(
                onNavUp = { navController.navigateUp() }
            )
        }
        composable(route = TicketNav.Export.route) {
            ExportReportScreen()
        }
        composable(route = TicketNav.ManageAccount.route) {
            AccountManageScreen(
                navigateToCreateAccount = {
                    navController.navigate(TicketNav.CreateAccount.route)
                },
                navigateToUpdateAccount = { userId ->
                    navController.navigate(TicketNav.UpdateAccount.createRoute(userId))
                }
            )
            onTitleChange("Kelola Pengguna")
        }
        composable(route = TicketNav.CreateAccount.route) {
            AccountsCreateScreen(
                onNavUp = { navController.navigateUp() }
            )
            onTitleChange("Buat Pengguna")
        }
        composable(
            route = TicketNav.UpdateAccount.route,
            arguments = listOf(navArgument("userId") { type = NavType.IntType })
        ) {
            val userId = it.arguments?.getInt("userId") ?: -1
            AccountsUpdateScreen(userId = userId,
                onNavUp = { navController.navigateUp() })
            onTitleChange("Perbarui Pengguna")
        }
    }
}