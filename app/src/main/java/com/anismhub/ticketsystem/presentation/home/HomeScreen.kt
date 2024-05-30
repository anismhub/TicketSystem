package com.anismhub.ticketsystem.presentation.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.anismhub.ticketsystem.data.DataDummy
import com.anismhub.ticketsystem.navigation.graph.HomeNavGraph
import com.anismhub.ticketsystem.presentation.components.BottomBar
import com.anismhub.ticketsystem.presentation.components.CreateFAB
import com.anismhub.ticketsystem.presentation.components.TabItem
import com.anismhub.ticketsystem.presentation.components.TicketItem

@Composable
fun HomeScreen(
    navigateToLogin: () -> Unit,
    navigateToCreate: () -> Unit,
    navigateToDetailTicket: () -> Unit,
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
        },
        floatingActionButton = {
            if (currentRoute == "home") {
                CreateFAB { navigateToCreate() }
            }
        }
    ) { paddingValues ->
        Box(modifier = modifier.padding(paddingValues)) {
            HomeNavGraph(navController = navController,
                navigateToLogin = navigateToLogin,
                navigateToDetailTicket = navigateToDetailTicket
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun HomeContent(
    navigateToDetailTicket: () -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val pagerState = rememberPagerState { TabItem.entries.size }
    LaunchedEffect(selectedTabIndex) {
        pagerState.animateScrollToPage(selectedTabIndex)
    }
    LaunchedEffect(pagerState.currentPage, pagerState.isScrollInProgress) {
        if (!pagerState.isScrollInProgress) {
            selectedTabIndex = pagerState.currentPage
        }
    }

    Column(
        modifier = modifier,
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
            modifier = Modifier.padding(horizontal = 24.dp)
        ) { }
        TabRow(selectedTabIndex = selectedTabIndex) {
            TabItem.entries.forEachIndexed { index, currentTab ->
                Tab(
                    selected = selectedTabIndex == index,
                    selectedContentColor = MaterialTheme.colorScheme.primary,
                    unselectedContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    onClick = { selectedTabIndex = index },
                    text = { Text(text = currentTab.title) },
                )
            }
        }
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxSize()
        ) { index ->
            LazyColumn(
                modifier = Modifier.padding(horizontal = 24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(bottom = 64.dp, top = 8.dp),
            ) {
                items(DataDummy.dummyTickets, key = { it.title }) {
                    TicketItem(
                        number = DataDummy.dummyTickets.indexOf(it) + 1,
                        title = it.title,
                        date = it.date,
                        priority = it.priority,
                        status = TabItem.entries[index].title,
                        onClick = { navigateToDetailTicket() }
                    )
                }
            }
        }
    }
}

