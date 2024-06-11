package com.anismhub.ticketsystem.domain.model

import com.anismhub.ticketsystem.data.remote.dto.TechProfileDataDTO

data class TechProfile(
    val error: Boolean,
    val status: Int,
    val message: String,
    val data: List<TechProfileData>
)
