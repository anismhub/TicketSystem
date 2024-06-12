package com.anismhub.ticketsystem.domain.model


data class Login(
    val error: Boolean,
    val status: Int,
    val message: String,
    val data: LoginData
)

data class LoginData(
    val userId: Int,
    val userName: String,
    val userFullName: String,
    val userRole: String,
    val accessToken: String
)