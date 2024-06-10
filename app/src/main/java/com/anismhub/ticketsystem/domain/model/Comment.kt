package com.anismhub.ticketsystem.domain.model

data class Comment(
    val commentId: Int,
    val commentTicket: Int,
    val commentCreatedBy: String,
    val commentCreatedAt: String,
    val commentContent: String
)
