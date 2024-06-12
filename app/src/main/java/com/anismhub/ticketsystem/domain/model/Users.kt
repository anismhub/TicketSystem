package com.anismhub.ticketsystem.domain.model

data class Users(
    val error: Boolean,
    val status: Int,
    val message: String,
    val data: List<ProfileData>
)

data class Profile(
    val error: Boolean,
    val status: Int,
    val message: String,
    val data: ProfileData
)

data class ProfileData(
    val userId: Int,
    val userFullName: String,
    val userName: String,
    val userRole: String,
    val departmentId: Int,
    val departmentName: String,
    val userPhone: String
)