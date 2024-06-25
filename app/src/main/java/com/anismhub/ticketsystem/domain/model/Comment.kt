package com.anismhub.ticketsystem.domain.model

data class Comment(
    val commentName: String,
    val commentUserRole: String,
    val commentTime: String,
    val commentContent: String,
    val commentImage: String
)
