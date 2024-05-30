package com.anismhub.ticketsystem.navigation.graph

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.anismhub.ticketsystem.presentation.home.HomeContent
import com.anismhub.ticketsystem.presentation.home.HomeScreen

@Composable
fun RootNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Graph.AUTHENTICATION,
        route = Graph.ROOT,
        modifier = modifier
    ) {
        authNavGraph(navController = navController)
        ticketNavGraph(navController = navController)
        composable(route = Graph.MAIN) {
            HomeScreen(
                navigateToLogin = {
                    navController.popBackStack()
                    navController.navigate(Graph.AUTHENTICATION)
                },
                navigateToCreate = {
                    navController.navigate(Graph.TICKET)
                },
                navigateToDetailTicket = {
                    navController.navigate(TicketScreen.Detail.route)
                }
            )
        }

    }
}

object Graph {
    const val ROOT = "root"
    const val AUTHENTICATION = "authentication"
    const val MAIN = "main"
    const val TICKET = "ticket"
}