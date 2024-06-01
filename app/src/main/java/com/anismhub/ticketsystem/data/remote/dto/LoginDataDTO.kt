package com.anismhub.ticketsystem.data.remote.dto

import com.google.gson.annotations.SerializedName

data class LoginDataDTO(
    @field:SerializedName("userId")
    val userId: Int,

    @field:SerializedName("userName")
    val userName: String,

    @field:SerializedName("userFullName")
    val userFullName: String,

    @field:SerializedName("userRole")
    val userRole: String,

    @field:SerializedName("accessToken")
    val accessToken: String
)
