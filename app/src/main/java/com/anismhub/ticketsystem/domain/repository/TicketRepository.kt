package com.anismhub.ticketsystem.domain.repository

import com.anismhub.ticketsystem.domain.model.DetailTicket
import com.anismhub.ticketsystem.domain.model.Response
import com.anismhub.ticketsystem.domain.model.Ticket
import com.anismhub.ticketsystem.utils.Resource
import kotlinx.coroutines.flow.Flow

interface TicketRepository {
    fun getOpenTickets(status: String = "Open"): Flow<Resource<Ticket>>
    fun getOnProgressTickets(status: String = "On Progress"): Flow<Resource<Ticket>>
    fun getClosedTickets(status: String = "Closed"): Flow<Resource<Ticket>>

    fun getTicketById(ticketId: Int): Flow<Resource<DetailTicket>>

    fun addTicket(
        ticketSubject: String,
        ticketDescription: String,
        ticketPriority: Int,
        ticketArea: Int,
        ticketCategory: Int
    ): Flow<Resource<Response>>
}