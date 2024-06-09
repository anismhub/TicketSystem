package com.anismhub.ticketsystem.domain.repository

import com.anismhub.ticketsystem.data.remote.dto.ResponseDTO
import com.anismhub.ticketsystem.domain.model.Response
import com.anismhub.ticketsystem.domain.model.Ticket
import com.anismhub.ticketsystem.utils.Resource
import kotlinx.coroutines.flow.Flow

interface TicketRepository {
    fun getTickets(status: String): Flow<Resource<Ticket>>

    fun addTicket(
        ticketSubject: String,
        ticketDescription: String,
        ticketPriority: Int,
        ticketArea: Int,
        ticketCategory: Int
    ): Flow<Resource<Response>>
}