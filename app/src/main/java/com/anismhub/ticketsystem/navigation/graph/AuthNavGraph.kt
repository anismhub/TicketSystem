package com.anismhub.ticketsystem.navigation.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.anismhub.ticketsystem.presentation.screen.signin.SignInScreen

fun NavGraphBuilder.authNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.AUTHENTICATION,
        startDestination = AuthNav.Signin.route
    ) {
        composable(route = AuthNav.Signin.route) {
            SignInScreen(
                navigateToHome = { navController.navigate(Graph.MAIN) }
            )
        }
    }
}

sealed class AuthNav(val route: String) {
    data object Signin : AuthNav("signin")
}