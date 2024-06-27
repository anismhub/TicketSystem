package com.anismhub.ticketsystem.presentation.screen.tickets.addticket

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anismhub.ticketsystem.domain.model.Response
import com.anismhub.ticketsystem.domain.repository.TicketRepository
import com.anismhub.ticketsystem.utils.Event
import com.anismhub.ticketsystem.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddTicketViewModel @Inject constructor(
    private val ticketRepository: TicketRepository
) : ViewModel() {
    private val _addTicket: MutableStateFlow<Event<Resource<Response>>> =
        MutableStateFlow(Event(Resource.None))
    val addTicket: StateFlow<Event<Resource<Response>>> = _addTicket

    fun addTicket(
        ticketSubject: String,
        ticketDescription: String,
        ticketPriority: String,
        ticketArea: Int,
        ticketCategory: Int
    ) {
        viewModelScope.launch {
            ticketRepository.addTicket(
                ticketSubject = ticketSubject,
                ticketDescription = ticketDescription,
                ticketPriority = ticketPriority,
                ticketArea = ticketArea,
                ticketCategory = ticketCategory
            ).collect {
                _addTicket.value = Event(it)
            }
        }
    }

}