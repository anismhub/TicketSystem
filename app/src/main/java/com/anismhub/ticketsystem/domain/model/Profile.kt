package com.anismhub.ticketsystem.domain.model

data class Profile(
    val error: Boolean,
    val status: Int,
    val message: String,
    val data: ProfileData
)
