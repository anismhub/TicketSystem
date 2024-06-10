package com.anismhub.ticketsystem.data.remote.dto

import com.google.gson.annotations.SerializedName

data class CommentDTO(
//    @field:SerializedName("commentId")
//    val commentId: Int,
//    @field:SerializedName("ticketId")
//    val commentTicket: Int,
    @field:SerializedName("commentName")
    val commentName: String,
    @field:SerializedName("commentTime")
    val commentTime: String,
    @field:SerializedName("commentContent")
    val commentContent: String
)
