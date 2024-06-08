package com.anismhub.ticketsystem.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ProfileDataDto(
    @field:SerializedName("userId")
    val userId: Int,
    @field:SerializedName("userFullName")
    val userFullName: String,
    @field:SerializedName("userName")
    val userName: String,
    @field:SerializedName("userRole")
    val userRole: String,
    @field:SerializedName("departmentId")
    val departmentId: Int,
    @field:SerializedName("departmentName")
    val departmentName: String,
    @field:SerializedName("userPhone")
    val userPhone: String
)