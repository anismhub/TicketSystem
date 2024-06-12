package com.anismhub.ticketsystem.data.remote.dto

import com.google.gson.annotations.SerializedName

data class UsersDTO(
    @field:SerializedName("error")
    val error: Boolean,

    @field:SerializedName("status")
    val status: Int,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("data")
    val data: List<ProfileDataDTO>
)

data class ProfileDTO(
    @field:SerializedName("error")
    val error: Boolean,

    @field:SerializedName("status")
    val status: Int,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("data")
    val data: ProfileDataDTO
)

data class ProfileDataDTO(
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