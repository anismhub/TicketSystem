package com.anismhub.ticketsystem.navigation.graph

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.anismhub.ticketsystem.navigation.BottomNav
import com.anismhub.ticketsystem.presentation.screen.home.HomeContent
import com.anismhub.ticketsystem.presentation.screen.notification.NotificationContent
import com.anismhub.ticketsystem.presentation.screen.settings.accounts.create.AccountsCreateContent
import com.anismhub.ticketsystem.presentation.screen.settings.accounts.manage.AccountManageContent
import com.anismhub.ticketsystem.presentation.screen.settings.accounts.update.AccountsUpdateContent
import com.anismhub.ticketsystem.presentation.screen.settings.exportreport.ExportReportScreen
import com.anismhub.ticketsystem.presentation.screen.settings.setting.SettingsContent
import com.anismhub.ticketsystem.presentation.screen.tickets.addticket.AddTicketContent
import com.anismhub.ticketsystem.presentation.screen.tickets.detailticket.DetailTicketContent

@Composable
fun BottomNavGraph(
    onTitleChange: (String) -> Unit,
    navController: NavHostController,
    navigateToLogin: () -> Unit,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = BottomNav.Home.route,
        route = Graph.MAIN,
        modifier = modifier
    ) {
        composable(route = BottomNav.Home.route) {
            HomeContent(
                navigateToDetailTicket = { title ->
                    navController.navigate(Graph.TICKET)
                    onTitleChange(title)
                })
        }
        composable(route = BottomNav.Notification.route) {
            NotificationContent()
        }
        composable(route = BottomNav.Settings.route) {
            SettingsContent(
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

fun NavGraphBuilder.ticketNavGraph(
    onTitleChange: (String) -> Unit,
    navController: NavHostController
) {
    navigation(
        route = Graph.TICKET,
        startDestination = TicketNav.Detail.route
    ) {
        composable(route = TicketNav.Detail.route) {
            DetailTicketContent()
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


sealed class TicketNav(val route: String) {
    data object Detail : TicketNav("detail")
    data object Create : TicketNav("create")
    data object Export : TicketNav("export")
    data object ManageAccount : TicketNav("manage_account")
    data object CreateAccount : TicketNav("create_account")
    data object UpdateAccount : TicketNav("update_account")
}