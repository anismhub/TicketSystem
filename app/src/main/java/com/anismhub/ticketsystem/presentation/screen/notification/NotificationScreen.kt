package com.anismhub.ticketsystem.presentation.screen.notification

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.anismhub.ticketsystem.domain.model.TicketData
import com.anismhub.ticketsystem.presentation.components.NotificationCard
import com.anismhub.ticketsystem.presentation.screen.tickets.TicketViewModel
import com.anismhub.ticketsystem.presentation.theme.MyTypography
import com.anismhub.ticketsystem.utils.Resource
import com.anismhub.ticketsystem.utils.toDateTime

@Composable
fun NotificationScreen(
    modifier: Modifier = Modifier,
    viewModel: TicketViewModel = hiltViewModel()
) {
    var listTicket by remember { mutableStateOf(emptyList<TicketData>()) }
    var listTicketOpen by remember { mutableStateOf(emptyList<TicketData>()) }
    var listTicketOnProgress by remember { mutableStateOf(emptyList<TicketData>()) }
    var listTicketClosed by remember { mutableStateOf(emptyList<TicketData>()) }
    val ticketOpen by viewModel.ticketOpen.collectAsStateWithLifecycle()
    val ticketOnProgress by viewModel.ticketOnProgress.collectAsStateWithLifecycle()
    val ticketClosed by viewModel.ticketClosed.collectAsStateWithLifecycle()
    var query by remember { mutableStateOf("") }
    LaunchedEffect(query) {
        viewModel.getOpenTicket(status = "Open", query = query)
        viewModel.getOnProgressTicket(status = "On Progress", query = query)
        viewModel.getClosedTicket(status = "Closed", query = query)
    }
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

    listTicket = listTicketOpen + listTicketOnProgress + listTicketClosed
    NotificationContent(
        data = listTicket,
        modifier = modifier
    )


}

@Composable
fun NotificationContent(
    data: List<TicketData>,
    modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(start = 24.dp, end = 24.dp, top = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Notifikasi", style = MyTypography.headlineMedium.copy(fontWeight = FontWeight.Bold))
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(bottom = 24.dp),
            modifier = Modifier
                .padding(top = 16.dp)
        ) {
            items(data, key = { it.ticketId }) {
                NotificationCard(message = "Tiket ${it.ticketId} telah dibuat ", date = it.ticketCreatedAt.toDateTime())
            }
        }
    }
}