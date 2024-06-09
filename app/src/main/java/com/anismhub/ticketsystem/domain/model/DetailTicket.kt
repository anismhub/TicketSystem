package com.anismhub.ticketsystem.domain.model

import com.google.gson.annotations.SerializedName

data class DetailTicket(
    val ticketId: Int,
    val ticketSubject: String,
    val ticketDescription: String,
    val ticketPriority: String,
    val ticketStatus: String,
    val ticketCategory: Int,
    val ticketArea: Int,
    val ticketCreatedBy: Int,
    val ticketCreatedAt: String,
    val ticketUpdateAt: String
)