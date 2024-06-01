package com.anismhub.ticketsystem.presentation.screen.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.anismhub.ticketsystem.navigation.graph.BottomNavGraph
import com.anismhub.ticketsystem.presentation.components.BottomNavBar
import com.anismhub.ticketsystem.presentation.components.CreateFAB

@Composable
fun MainScreen(
    navigateToLogin: () -> Unit,
    navigateToCreate: () -> Unit,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val currentRoute = currentDestination?.route

    Scaffold(
        bottomBar = {
            BottomNavBar(
                navHostController = navController,
                currentDestination = currentDestination
            )
        },
        floatingActionButton = {
            if (currentRoute == "home") {
                CreateFAB { navigateToCreate() }
            }
        }
    ) { paddingValues ->
        Box(modifier = modifier.padding(paddingValues)) {
            BottomNavGraph(
                navController = navController,
                navigateToLogin = navigateToLogin,
            )
        }
    }

}