package com.anismhub.ticketsystem.data.remote.dto

import com.google.gson.annotations.SerializedName

data class DetailTicketDTO(
    @field:SerializedName("ticketId")
    val ticketId: Int,

    @field:SerializedName("ticketSubject")
    val ticketSubject: String,

    @field:SerializedName("ticketDescription")
    val ticketDescription: String,

    @field:SerializedName("ticketPriority")
    val ticketPriority: String,

    @field:SerializedName("ticketStatus")
    val ticketStatus: String,

    @field:SerializedName("ticketCategory")
    val ticketCategory: Int,

    @field:SerializedName("ticketArea")
    val ticketArea: Int,

    @field:SerializedName("ticketCreatedBy")
    val ticketCreatedBy: Int,

    @field:SerializedName("ticketCreateAt")
    val ticketCreatedAt: String,

    @field:SerializedName("ticketUpdateAt")
    val ticketUpdateAt: String
)
