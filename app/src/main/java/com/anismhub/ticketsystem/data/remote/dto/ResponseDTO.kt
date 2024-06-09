package com.anismhub.ticketsystem.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ResponseDTO(
    @field:SerializedName("error")
    val error: Boolean,
    @field:SerializedName("status")
    val status: Int,
    @field:SerializedName("message")
    val message: String
)
