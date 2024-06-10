package com.anismhub.ticketsystem.data.remote.dto

import com.anismhub.ticketsystem.domain.model.Comment
import com.anismhub.ticketsystem.domain.model.Resolution
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
    val ticketCategory: String,

    @field:SerializedName("ticketArea")
    val ticketArea: String,

    @field:SerializedName("ticketCreatedBy")
    val ticketCreatedBy: String,

    @field:SerializedName("ticketCreatedAt")
    val ticketCreatedAt: String,

    @field:SerializedName("ticketUpdatedAt")
    val ticketUpdatedAt: String,

    @field:SerializedName("comments")
    val comments: List<CommentDTO>,

    @field:SerializedName("resolution")
    val resolution: List<ResolutionDTO>
)
