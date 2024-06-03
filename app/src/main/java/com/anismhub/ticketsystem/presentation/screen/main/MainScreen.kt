package com.anismhub.ticketsystem.presentation.screen.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.anismhub.ticketsystem.navigation.BottomNavItemRoute
import com.anismhub.ticketsystem.navigation.graph.BottomNavGraph
import com.anismhub.ticketsystem.presentation.components.BottomNavBar
import com.anismhub.ticketsystem.presentation.components.CreateFAB
import com.anismhub.ticketsystem.presentation.components.MyTopAppBar

@Composable
fun MainScreen(
    navigateToLogin: () -> Unit,
    navigateToCreate: () -> Unit,
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    var title by rememberSaveable { mutableStateOf("") }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val currentRoute = currentDestination?.route

    Scaffold(
        topBar = {
            MyTopAppBar(
                title = title,
                navController = navController,
                topBarDestination = currentRoute !in BottomNavItemRoute
            )
        },
        bottomBar = {
            BottomNavBar(
                navHostController = navController,
                currentDestination = currentDestination
            )
        },
        floatingActionButton = {
            if (currentRoute == "home") {
                CreateFAB { navigateToCreate()
                }
            }
        }
    ) { paddingValues ->
        Box(modifier = modifier.padding(paddingValues)) {
            BottomNavGraph(
                onTitleChange = { title = it },
                navController = navController,
                navigateToLogin = navigateToLogin,
            )
        }
    }
}