package com.anismhub.ticketsystem.domain.model

data class Ticket(
    val error: Boolean,
    val status: Int,
    val message: String,
    val data: List<TicketData>
)

data class TicketData(
    val ticketId: Int,
    val ticketSubject: String,
    val ticketPriority: String,
    val ticketStatus: String,
    val ticketCreatedAt: String
)