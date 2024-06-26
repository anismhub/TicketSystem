package com.anismhub.ticketsystem.data.remote.dto

import com.google.gson.annotations.SerializedName

data class CommentDTO(
    @field:SerializedName("commentName")
    val commentName: String,
    @field:SerializedName("commentUserRole")
    val commentUserRole: String,
    @field:SerializedName("commentTime")
    val commentTime: String,
    @field:SerializedName("commentContent")
    val commentContent: String,
    @field:SerializedName("commentImage")
    val commentImage: String?
)
