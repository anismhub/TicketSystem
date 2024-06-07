package com.anismhub.ticketsystem.data.mapper

import com.anismhub.ticketsystem.data.remote.dto.TicketDTO
import com.anismhub.ticketsystem.data.remote.dto.TicketDataDTO
import com.anismhub.ticketsystem.domain.model.Ticket
import com.anismhub.ticketsystem.domain.model.TicketData

fun TicketDataDTO.toTicketData(): TicketData {
    return TicketData(
        ticketId = ticketId,
        ticketSubject = ticketSubject,
        ticketPriority = ticketPriority,
        ticketStatus = ticketStatus,
        ticketCreatedAt = ticketCreatedAt
    )
}

fun TicketDTO.toTicket(): Ticket {
    return Ticket(
        error = error,
        status = status,
        message = message,
        data = data.toTicketData()
    )
}