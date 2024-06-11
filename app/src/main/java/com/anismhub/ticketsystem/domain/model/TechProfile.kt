package com.anismhub.ticketsystem.domain.model

data class TechProfile(
    val error: Boolean,
    val status: Int,
    val message: String,
    val data: List<TechProfileData>
)
