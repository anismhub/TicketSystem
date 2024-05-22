package com.anismhub.ticketsystem.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ConfirmationNumber
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import com.anismhub.ticketsystem.navigation.BottomNavItem

@Composable
fun BottomBar(
    navHostController: NavHostController,
    currentDestination: NavDestination?,
    modifier: Modifier = Modifier
) {
    NavigationBar(modifier = modifier) {
        BottomNavItem.forEach { item ->
            val selected = item.route == currentDestination?.route
            NavigationBarItem(
                selected = selected,
                onClick = { navHostController.navigate(item.route) },
                icon = { Icon(item.icon,
                    contentDescription = item.title) })
        }
    }
}