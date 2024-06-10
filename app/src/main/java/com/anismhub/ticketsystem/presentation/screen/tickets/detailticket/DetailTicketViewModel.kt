package com.anismhub.ticketsystem.presentation.screen.tickets.detailticket

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anismhub.ticketsystem.domain.model.Comment
import com.anismhub.ticketsystem.domain.model.DetailTicket
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
class DetailTicketViewModel @Inject constructor(
    private val ticketRepository: TicketRepository
): ViewModel() {
    private val _detailTicket: MutableStateFlow<Resource<DetailTicket>> =
        MutableStateFlow(Resource.None)
    val detailTicket: StateFlow<Resource<DetailTicket>> = _detailTicket

    private val _comments: MutableStateFlow<Event<Resource<Response>>> = MutableStateFlow(Event(Resource.None))
    val comments: StateFlow<Event<Resource<Response>>> = _comments

    private val _closeTicket: MutableStateFlow<Event<Resource<Response>>> = MutableStateFlow(Event(Resource.None))
    val closeTicket: StateFlow<Event<Resource<Response>>> = _closeTicket

    fun getTicketById(ticketId: Int) {
        viewModelScope.launch {
            ticketRepository.getTicketById(ticketId).collect {
                _detailTicket.value = it
            }
        }
    }

    fun addComment(ticketId: Int, comment: String) {
        viewModelScope.launch {
            ticketRepository.addComment(ticketId, comment).collect {
                _comments.value = Event(it)
            }
        }
    }

    fun closeTicket(ticketId: Int, resolusi: String) {
        viewModelScope.launch {
            ticketRepository.closeTicket(ticketId, resolusi).collect {
                _closeTicket.value = Event(it)
            }
        }
    }
}