package com.anismhub.ticketsystem.domain.model

data class LoginData(
    val userId: Int,
    val userName: String,
    val userFullName: String,
    val userRole: String,
    val accessToken: String
)
