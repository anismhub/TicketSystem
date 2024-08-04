package com.anismhub.ticketsystem.data.mapper

import com.anismhub.ticketsystem.data.remote.dto.CommentDTO
import com.anismhub.ticketsystem.data.remote.dto.DetailTicketDTO
import com.anismhub.ticketsystem.data.remote.dto.DetailTicketDataDTO
import com.anismhub.ticketsystem.data.remote.dto.ResolutionDTO
import com.anismhub.ticketsystem.data.remote.dto.ResponseDTO
import com.anismhub.ticketsystem.data.remote.dto.TicketDTO
import com.anismhub.ticketsystem.data.remote.dto.TicketDataDTO
import com.anismhub.ticketsystem.domain.model.Comment
import com.anismhub.ticketsystem.domain.model.DetailTicket
import com.anismhub.ticketsystem.domain.model.DetailTicketData
import com.anismhub.ticketsystem.domain.model.Resolution
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
            ticketCreatedAt = ticketDataDTO.ticketCreatedAt,
            ticketAreaCode = ticketDataDTO.ticketAreaCode,
            ticketCategoryCode = ticketDataDTO.ticketCategoryCode
        )
    }
}

fun TicketDTO.toTicket(): Ticket {
    return Ticket(
        error = error, status = status, message = message, data = data.toListTicketData()
    )
}

fun DetailTicketDTO.toDetailTicket(): DetailTicket {
    return DetailTicket(
        error = error, status = status, message = message, data = data.toDetailTicketData()
    )
}

fun DetailTicketDataDTO.toDetailTicketData(): DetailTicketData {
    return DetailTicketData(
        ticketId = ticketId,
        ticketSubject = ticketSubject,
        ticketDescription = ticketDescription,
        ticketPriority = ticketPriority,
        ticketStatus = ticketStatus,
        ticketCategory = ticketCategory,
        ticketCategoryCode = ticketCategoryCode,
        ticketArea = ticketArea,
        ticketAreaCode = ticketAreaCode,
        ticketCreatedBy = ticketCreatedBy,
        ticketDepartmentBy = ticketDepartmentBy,
        ticketCreatedAt = ticketCreatedAt,
        ticketUpdateAt = ticketUpdatedAt,
        ticketAssignedTo = ticketAssignedTo,
        comments = comments.map { it.toComment() }
    )
}

fun ResponseDTO.toResponse(): Response {
    return Response(
        error = error, status = status, message = message
    )
}

fun CommentDTO.toComment(): Comment {
    return Comment(
        commentTime = commentTime,
        commentUserRole = commentUserRole,
        commentName = commentName,
        commentContent = commentContent,
        commentImage = commentImage
    )
}

fun ResolutionDTO.toResolution(): Resolution {
    return Resolution(
        resolutionResolvedBy = resolutionResolvedBy,
        resolutionResolvedAt = resolutionResolvedAt,
        resolutionContent = resolutionContent
    )
}