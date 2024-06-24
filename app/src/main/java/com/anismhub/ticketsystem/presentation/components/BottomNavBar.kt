package com.anismhub.ticketsystem.presentation.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import com.anismhub.ticketsystem.navigation.BottomNavItem

@Composable
fun BottomNavBar(
    navHostController: NavHostController,
    currentDestination: NavDestination?,
    showBottomNavBar: Boolean,
    modifier: Modifier = Modifier
) {
    if (showBottomNavBar) {
        NavigationBar(modifier = modifier) {
            BottomNavItem.forEach { item ->
                val selected = item.route == currentDestination?.route
                NavigationBarItem(
                    selected = selected,
                    onClick = {
                        if (!selected) {
                            navHostController.navigate(item.route) {
                                popUpTo(navHostController.graph.startDestinationId)
                                launchSingleTop = true
                            }
                        }
                    },
                    icon = {
                        Icon(
                            painter = painterResource(id = item.icon),
                            contentDescription = item.title
                        )
                    },
                    label = { Text(text = item.title) })
            }
        }
    }
}