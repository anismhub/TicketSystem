package com.anismhub.ticketsystem.domain.model


data class Login(
    val error: Boolean,
    val status: Int,
    val message: String,
    val data: LoginData
)
