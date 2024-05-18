package com.anismhub.ticketsystem.navigation.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.anismhub.ticketsystem.presentation.signin.SignInScreen

fun NavGraphBuilder.authNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.AUTHENTICATION,
        startDestination = AuthScreen.Signin.route
    ) {
        composable(route = AuthScreen.Signin.route) {
            SignInScreen(
                navigateToHome = { navController.navigate(Graph.MAIN) }
            )
        }
    }
}

sealed class AuthScreen(val route: String) {
    data object Signin : AuthScreen("signin")
}