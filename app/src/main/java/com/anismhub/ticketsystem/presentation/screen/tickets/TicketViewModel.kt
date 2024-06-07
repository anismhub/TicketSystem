package com.anismhub.ticketsystem.presentation.screen.tickets

import androidx.lifecycle.ViewModel
import com.anismhub.ticketsystem.domain.repository.TicketRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TicketViewModel @Inject constructor(
    private val ticketRepository: TicketRepository
) : ViewModel() {

}