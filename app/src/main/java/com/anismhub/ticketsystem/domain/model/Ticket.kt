package com.anismhub.ticketsystem.domain.model

data class Ticket(
    val error: Boolean,
    val status: Int,
    val message: String,
    val data: TicketData
)
