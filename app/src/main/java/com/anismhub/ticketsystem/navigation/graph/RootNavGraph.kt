package com.anismhub.ticketsystem.navigation.graph

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.anismhub.ticketsystem.presentation.screen.main.MainScreen

@Composable
fun RootNavGraph(
    startDestination: String,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    var title by rememberSaveable { mutableStateOf("") }

    NavHost(
        navController = navController,
        startDestination = startDestination,
        route = Graph.ROOT,
        modifier = modifier
    ) {
        authNavGraph(navController = navController)
        ticketNavGraph(navController = navController, onTitleChange = { title = it })
        composable(route = Graph.MAIN) {
            MainScreen(
                navigateToLogin = {
                    navController.popBackStack()
                    navController.navigate(Graph.AUTHENTICATION)
                },
                navigateToCreate = {
                    navController.navigate(TicketNav.Create.route)
                },
            )
        }
        composable(route = Graph.NONE) {}
    }
}

object Graph {
    const val ROOT = "root"
    const val AUTHENTICATION = "authentication"
    const val MAIN = "main"
    const val TICKET = "ticket"
    const val NONE = "none"
}