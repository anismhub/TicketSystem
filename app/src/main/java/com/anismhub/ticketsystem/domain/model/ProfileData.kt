package com.anismhub.ticketsystem.domain.model

data class ProfileData(
    val userId: Int,
    val userFullName: String,
    val userName: String,
    val userRole: String,
    val departmentId: Int,
    val departmentName: String,
    val userPhone: String
)
