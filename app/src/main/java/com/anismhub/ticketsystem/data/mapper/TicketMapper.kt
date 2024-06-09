package com.anismhub.ticketsystem.data.mapper

import com.anismhub.ticketsystem.data.remote.dto.DetailTicketDTO
import com.anismhub.ticketsystem.data.remote.dto.ResponseDTO
import com.anismhub.ticketsystem.data.remote.dto.TicketDTO
import com.anismhub.ticketsystem.data.remote.dto.TicketDataDTO
import com.anismhub.ticketsystem.domain.model.DetailTicket
import com.anismhub.ticketsystem.domain.model.Response
import com.anismhub.ticketsystem.domain.model.Ticket
import com.anismhub.ticketsystem.domain.model.TicketData

fun List<TicketDataDTO>.toListTicketData(): List<TicketData> {
    return this.map { ticketDataDTO ->
        TicketData(
            ticketId = ticketDataDTO.ticketId,
            ticketSubject = ticketDataDTO.ticketSubject,
            ticketPriority = ticketDataDTO.ticketPriority,
            ticketStatus = ticketDataDTO.ticketStatus,
            ticketCreatedAt = ticketDataDTO.ticketCreatedAt
        )
    }
}

fun TicketDTO.toTicket(): Ticket {
    return Ticket(
        error = error,
        status = status,
        message = message,
        data = data.toListTicketData()
    )
}

fun DetailTicketDTO.toDetailTicket(): DetailTicket {
    return DetailTicket(
        ticketId = ticketId,
        ticketSubject = ticketSubject,
        ticketDescription = ticketDescription,
        ticketPriority = ticketPriority,
        ticketStatus = ticketStatus,
        ticketCategory = ticketCategory,
        ticketArea = ticketArea,
        ticketCreatedBy = ticketCreatedBy,
        ticketCreatedAt = ticketCreatedAt,
        ticketUpdateAt = ticketUpdateAt
    )
}

fun ResponseDTO.toResponse(): Response {
    return Response(
        error = error,
        status = status,
        message = message
    )
}