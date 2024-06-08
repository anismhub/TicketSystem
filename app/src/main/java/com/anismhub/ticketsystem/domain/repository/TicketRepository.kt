package com.anismhub.ticketsystem.domain.repository

import com.anismhub.ticketsystem.domain.model.Ticket
import com.anismhub.ticketsystem.utils.Resource
import kotlinx.coroutines.flow.Flow

interface TicketRepository {
    fun getTickets(status: String): Flow<Resource<Ticket>>
}