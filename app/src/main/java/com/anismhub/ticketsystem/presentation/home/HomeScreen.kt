package com.anismhub.ticketsystem.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.anismhub.ticketsystem.data.DataDummy
import com.anismhub.ticketsystem.navigation.graph.HomeNavGraph
import com.anismhub.ticketsystem.presentation.components.BottomBar
import com.anismhub.ticketsystem.presentation.components.TicketItem
import com.anismhub.ticketsystem.presentation.theme.TicketSystemTheme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val currentRoute = currentDestination?.route

    Scaffold(
        bottomBar = {
            BottomBar(
                navHostController = navController,
                currentDestination = currentDestination
            )
        }
    ) { paddingValues ->
        Box(modifier = modifier.padding(paddingValues)) {
            HomeNavGraph(navController = navController)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeContent(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.spacedBy(2.dp)
        //.verticalScroll(rememberScrollState())
    ) {
        SearchBar(
            query = "",
            onQueryChange = {},
            onSearch = {},
            active = false,
            onActiveChange = {},
            leadingIcon = {
                Icon(imageVector = Icons.Rounded.Search, contentDescription = "Search Icon")
            },
            placeholder = {
                Text(text = "Search")
            },
            modifier = modifier
        ) { }
        Spacer(modifier = Modifier.weight(1f))
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(bottom = 24.dp),
            modifier = Modifier
                .padding(top = 16.dp)
        ) {
            items(DataDummy.dummyTickets, key = { it.title }) {
                TicketItem(
                    number = DataDummy.dummyTickets.indexOf(it) + 1,
                    title = it.title,
                    date = it.date,
                    priority = it.priority,
                    status = it.status
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun HomeScreenPreview() {
    TicketSystemTheme {
        HomeContent()
    }
}