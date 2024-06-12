package com.anismhub.ticketsystem.data.remote.dto

import com.google.gson.annotations.SerializedName

data class TicketDTO(
    @field:SerializedName("error")
    val error: Boolean,

    @field:SerializedName("status")
    val status: Int,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("data")
    val data: List<TicketDataDTO>
)

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