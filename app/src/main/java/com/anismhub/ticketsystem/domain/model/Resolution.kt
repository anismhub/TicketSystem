package com.anismhub.ticketsystem.domain.model

data class Resolution(
    val resolutionId: Int,
    val resolutionTicket: Int,
    val resolutionResolvedBy: String,
    val resolutionResolvedAt: String,
    val resolutionContent: String
)
