package com.anismhub.ticketsystem.domain.model

data class ProfileData(
    val userId: Int,
    val userFullname: String,
    val userName: String,
    val userRole: String,
    val departmentId: Int,
    val departmentName: String,
    val userPhone: String? = null
)
