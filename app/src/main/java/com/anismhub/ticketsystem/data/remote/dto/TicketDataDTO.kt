package com.anismhub.ticketsystem.data.remote.dto

import com.google.gson.annotations.SerializedName

data class TicketDataDTO(
    @field:SerializedName("ticketId")
    val ticketId: Int,
    @field:SerializedName("ticketSubject")
    val ticketSubject: String,
    @field:SerializedName("ticketPriority")
    val ticketPriority: String,
    @field:SerializedName("ticketStatus")
    val ticketStatus: String,
    @field:SerializedName("ticketCreateAt")
    val ticketCreatedAt: String
)
