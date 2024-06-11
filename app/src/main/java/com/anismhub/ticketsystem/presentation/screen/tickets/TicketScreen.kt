package com.anismhub.ticketsystem.presentation.screen.tickets

import android.util.Log
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.anismhub.ticketsystem.domain.model.TicketData
import com.anismhub.ticketsystem.presentation.components.MySearchBar
import com.anismhub.ticketsystem.presentation.components.TabItem
import com.anismhub.ticketsystem.presentation.components.TicketItem
import com.anismhub.ticketsystem.utils.Resource
import com.anismhub.ticketsystem.utils.toDateTime

@Composable
fun TicketScreen(
    navigateToDetailTicket: (title: String, ticketId: Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: TicketViewModel = hiltViewModel()
) {
    var listTicketOpen by remember { mutableStateOf(emptyList<TicketData>()) }
    var listTicketOnProgress by remember { mutableStateOf(emptyList<TicketData>()) }
    var listTicketClosed by remember { mutableStateOf(emptyList<TicketData>()) }

    val ticketOpen by viewModel.ticketOpen.collectAsStateWithLifecycle()
    val ticketOnProgress by viewModel.ticketOnProgress.collectAsStateWithLifecycle()
    val ticketClosed by viewModel.ticketClosed.collectAsStateWithLifecycle()

    viewModel.getOpenTicket()
    viewModel.getOnProgressTicket()
    viewModel.getClosedTicket()

    when (val result = ticketOpen) {
        is Resource.Loading -> {
            Log.d("Open Loading", "Ticket Open Loading : $result")
        }

        is Resource.Success -> {
            listTicketOpen = result.data.data
            Log.d("Open Success", "Ticket Open: ${result.data.data}")
        }

        is Resource.Error -> {
            Log.d("Open Error", "Ticket Open Error: ${result.error}")
        }

        else -> {}
    }

    when (val result = ticketOnProgress) {
        is Resource.Loading -> {
            Log.d("Progress Loading", "Ticket Progress Loading : $result")
        }

        is Resource.Success -> {
            listTicketOnProgress = result.data.data
            Log.d("Progress Success", "Ticket Progress: ${result.data.data}")
        }

        is Resource.Error -> {
            Log.d("Progress Error", "Ticket Progress Error: ${result.error}")
        }

        else -> {}
    }

    when (val result = ticketClosed) {
        is Resource.Loading -> {
            Log.d("Closed Loading", "Ticket Closed Loading : $result")
        }

        is Resource.Success -> {
            listTicketClosed = result.data.data
            Log.d("Closed Success", "Ticket Closed: ${result.data.data}")
        }

        is Resource.Error -> {
            Log.d("Closed Error", "Ticket Closed Error: ${result.error}")
        }

        else -> {}
    }

    TicketContent(
        ticketOpen = listTicketOpen,
        ticketOnProgress = listTicketOnProgress,
        ticketClosed = listTicketClosed,
        navigateToDetailTicket = navigateToDetailTicket,
        modifier = modifier
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TicketContent(
    ticketOpen: List<TicketData>,
    ticketOnProgress: List<TicketData>,
    ticketClosed: List<TicketData>,
    navigateToDetailTicket: (title: String, ticketId: Int) -> Unit,
    modifier: Modifier = Modifier,
) {
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
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(2.dp)
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
                    onClick = { selectedTabIndex = index },
                    text = { Text(text = currentTab.title) },
                )
            }
        }
        HorizontalPager(
            state = pagerState,
            verticalAlignment = Alignment.Top,
            modifier = Modifier.fillMaxSize()
        ) { index ->
            LazyColumn(
                modifier = Modifier.padding(horizontal = 24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(bottom = 64.dp, top = 8.dp),
            ) {
                when (index) {
                    0 -> {
                        items(ticketOpen.sortedByDescending { it.ticketId }, key = { it.ticketId }) {
                            TicketItem(
                                number = it.ticketId,
                                title = it.ticketSubject,
                                date = it.ticketCreatedAt.toDateTime(),
                                priority = it.ticketPriority,
                                status = it.ticketStatus,
                                onClick = { navigateToDetailTicket("Detail Tiket", it.ticketId) }
                            )
                        }
                    }

                    1 -> {
                        items(ticketOnProgress.sortedByDescending { it.ticketId }, key = { it.ticketId }) {
                            TicketItem(
                                number = it.ticketId,
                                title = it.ticketSubject,
                                date = it.ticketCreatedAt.toDateTime(),
                                priority = it.ticketPriority,
                                status = it.ticketStatus,
                                onClick = { navigateToDetailTicket("Detail Tiket", it.ticketId) }
                            )
                        }
                    }

                    2 -> {
                        items(ticketClosed.sortedByDescending { it.ticketId }, key = { it.ticketId }) {
                            TicketItem(
                                number = it.ticketId,
                                title = it.ticketSubject,
                                date = it.ticketCreatedAt.toDateTime(),
                                priority = it.ticketPriority,
                                status = it.ticketStatus,
                                onClick = { navigateToDetailTicket("Detail Tiket", it.ticketId) }
                            )
                        }
                    }
                }
            }
        }
    }
}

