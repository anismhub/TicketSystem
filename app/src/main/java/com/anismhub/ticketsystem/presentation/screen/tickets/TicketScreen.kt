package com.anismhub.ticketsystem.presentation.screen.tickets

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.anismhub.ticketsystem.data.DataDummy
import com.anismhub.ticketsystem.domain.model.TicketData
import com.anismhub.ticketsystem.presentation.components.MySearchBar
import com.anismhub.ticketsystem.presentation.components.TabItem
import com.anismhub.ticketsystem.presentation.components.TicketItem
import com.anismhub.ticketsystem.utils.Resource
import kotlin.math.log

@Composable
fun TicketScreen(
) {

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TicketContent(
    navigateToDetailTicket: (title: String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: TicketViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    val ticket by viewModel.ticket.collectAsStateWithLifecycle()
    var listTicketOpen by remember { mutableStateOf(emptyList<TicketData>()) }
    var listTicketOnProgress by remember { mutableStateOf(emptyList<TicketData>()) }
    var listTicketClosed by remember { mutableStateOf(emptyList<TicketData>()) }

    ticket.let {
        if (!it.hasBeenHandled) {
            when (val unhandled = it.getContentIfNotHandled()) {
                is Resource.Loading -> {

                }

                is Resource.Error -> {
                    Toast.makeText(context, unhandled.error, Toast.LENGTH_SHORT).show()
                }

                is Resource.Success -> {
                    listTicketOpen =
                        unhandled.data.data.filter { ticketData -> ticketData.ticketStatus == "Open" }
                    listTicketOnProgress =
                        unhandled.data.data.filter { ticketData -> ticketData.ticketStatus == "On Progress" }
                    listTicketClosed =
                        unhandled.data.data.filter { ticketData -> ticketData.ticketStatus == "Closed" }

                    Log.d("Ticket Open", "Ticket Open: $listTicketOpen")
                    Log.d("Ticket On Progress", "Ticket On Progress: $listTicketOnProgress")
                }

                else -> {}
            }

        }
    }

    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val pagerState = rememberPagerState { TabItem.entries.size }
    var query by remember { mutableStateOf("") }

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
        MySearchBar(
            query = query,
            onQueryChange = { query = it },
            modifier = Modifier.padding(horizontal = 24.dp)
        )
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
                val openTicket = DataDummy.tickets.filter { it.status == "Open" }
                val onProgressTicket = DataDummy.tickets.filter { it.status == "On Progress" }
                val closedTicket = DataDummy.tickets.filter { it.status == "Closed" }
                when (index) {
                    0 -> {
                        items(openTicket, key = { it.title }) {
                            TicketItem(
                                number = openTicket.indexOf(it) + 1,
                                title = it.title,
                                date = it.date,
                                priority = it.priority,
                                status = it.status,
                                onClick = { navigateToDetailTicket("Detail Tiket") }
                            )
                        }
                    }

                    1 -> {
                        items(onProgressTicket, key = { it.title }) {
                            TicketItem(
                                number = DataDummy.tickets.indexOf(it) + 1,
                                title = it.title,
                                date = it.date,
                                priority = it.priority,
                                status = it.status,
                                onClick = { navigateToDetailTicket("Detail Tiket") }
                            )
                        }
                    }

                    2 -> {
                        items(closedTicket, key = { it.title }) {
                            TicketItem(
                                number = DataDummy.tickets.indexOf(it) + 1,
                                title = it.title,
                                date = it.date,
                                priority = it.priority,
                                status = it.status,
                                onClick = { navigateToDetailTicket("Detail Tiket") }
                            )
                        }
                    }
                }
            }
        }
    }
}

