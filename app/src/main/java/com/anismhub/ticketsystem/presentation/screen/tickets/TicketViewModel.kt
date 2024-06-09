package com.anismhub.ticketsystem.presentation.screen.tickets

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anismhub.ticketsystem.domain.model.Ticket
import com.anismhub.ticketsystem.domain.repository.TicketRepository
import com.anismhub.ticketsystem.utils.Event
import com.anismhub.ticketsystem.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TicketViewModel @Inject constructor(
    private val ticketRepository: TicketRepository
) : ViewModel() {

    private val _ticket: MutableStateFlow<Event<Resource<Ticket>>> = MutableStateFlow(Event(Resource.None))
    val ticket: MutableStateFlow<Event<Resource<Ticket>>> = _ticket

    init {
        getTicket("Open")
        getTicket("On Progress")
        getTicket("Closed")
    }
    private fun getTicket(status: String){
        viewModelScope.launch {
            ticketRepository.getTickets(status).collect {
                _ticket.value = Event(it)
            }
        }
    }
}