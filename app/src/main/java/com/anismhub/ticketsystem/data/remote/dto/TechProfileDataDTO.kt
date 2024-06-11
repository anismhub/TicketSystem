package com.anismhub.ticketsystem.data.remote.dto

import com.google.gson.annotations.SerializedName

data class TechProfileDataDTO(
    @field:SerializedName("userId")
    val userId: Int,
    @field:SerializedName("userFullName")
    val userFullName: String,
)