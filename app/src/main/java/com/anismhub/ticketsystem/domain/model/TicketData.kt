package com.anismhub.ticketsystem.domain.model

data class TicketData(
    val ticketId: Int,
    val ticketSubject: String,
    val ticketPriority: String,
    val ticketStatus: String,
    val ticketCreatedAt: String
)
