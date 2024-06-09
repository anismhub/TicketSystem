package com.anismhub.ticketsystem.navigation.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.anismhub.ticketsystem.navigation.TicketNav
import com.anismhub.ticketsystem.presentation.screen.settings.accounts.AccountManageContent
import com.anismhub.ticketsystem.presentation.screen.settings.accounts.create.AccountsCreateContent
import com.anismhub.ticketsystem.presentation.screen.settings.accounts.update.AccountsUpdateContent
import com.anismhub.ticketsystem.presentation.screen.settings.exportreport.ExportReportScreen
import com.anismhub.ticketsystem.presentation.screen.tickets.addticket.AddTicketContent
import com.anismhub.ticketsystem.presentation.screen.tickets.detailticket.DetailTicketScreen


fun NavGraphBuilder.ticketNavGraph(
    onTitleChange: (String) -> Unit,
    navController: NavHostController
) {
    navigation(
        route = Graph.TICKET,
        startDestination = TicketNav.Detail.route
    ) {
        composable(route = TicketNav.Detail.route,
            arguments = listOf(navArgument("ticketId") {type = NavType.IntType})
        ) {
            val ticketId = it.arguments?.getInt("ticketId") ?: 0
            DetailTicketScreen(ticketId = ticketId)
        }
        composable(route = TicketNav.Create.route) {
            AddTicketContent(
                onNavUp = { navController.navigateUp() }
            )
        }
        composable(route = TicketNav.Export.route) {
            ExportReportScreen()
        }
        composable(route = TicketNav.ManageAccount.route) {
            AccountManageContent(
                navigateToCreateAccount = {
                    navController.navigate(TicketNav.CreateAccount.route)
                },
                navigateToUpdateAccount = {
                    navController.navigate(TicketNav.UpdateAccount.route)
                }
            )
            onTitleChange("Kelola Pengguna")
        }
        composable(route = TicketNav.CreateAccount.route) {
            AccountsCreateContent()
            onTitleChange("Buat Pengguna")
        }
        composable(route = TicketNav.UpdateAccount.route) {
            AccountsUpdateContent()
            onTitleChange("Perbarui Pengguna")
        }
    }
}