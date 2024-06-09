package com.anismhub.ticketsystem.presentation.screen.tickets.detailticket

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anismhub.ticketsystem.domain.model.DetailTicket
import com.anismhub.ticketsystem.domain.repository.TicketRepository
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

    fun getTicketById(ticketId: Int) {
        viewModelScope.launch {
            ticketRepository.getTicketById(ticketId).collect {
                _detailTicket.value = it
            }
        }
    }
}