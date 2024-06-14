package com.anismhub.ticketsystem.presentation.screen.tickets

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anismhub.ticketsystem.domain.model.Ticket
import com.anismhub.ticketsystem.domain.repository.TicketRepository
import com.anismhub.ticketsystem.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TicketViewModel @Inject constructor(
    private val ticketRepository: TicketRepository
) : ViewModel() {

    private val _ticketOpen: MutableStateFlow<Resource<Ticket>> = MutableStateFlow(Resource.None)
    val ticketOpen: MutableStateFlow<Resource<Ticket>> = _ticketOpen

    private val _ticketOnProgress: MutableStateFlow<Resource<Ticket>> =
        MutableStateFlow(Resource.None)
    val ticketOnProgress: MutableStateFlow<Resource<Ticket>> = _ticketOnProgress

    private val _ticketClosed: MutableStateFlow<Resource<Ticket>> = MutableStateFlow(Resource.None)
    val ticketClosed: MutableStateFlow<Resource<Ticket>> = _ticketClosed


    fun getOpenTicket(status: String, query: String? = null) {
        viewModelScope.launch {
            ticketRepository.getOpenTickets(status, query).collect {
                _ticketOpen.value = it
            }
        }
    }

    fun getOnProgressTicket(status: String, query: String? = null) {
        viewModelScope.launch {
            ticketRepository.getOnProgressTickets(status, query).collect {
                _ticketOnProgress.value = it
            }
        }
    }

    fun getClosedTicket(status: String, query: String? = null) {
        viewModelScope.launch {
            ticketRepository.getClosedTickets(status,query).collect {
                _ticketClosed.value = it
            }
        }
    }
}