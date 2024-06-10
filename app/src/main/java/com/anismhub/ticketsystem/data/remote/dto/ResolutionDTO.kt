package com.anismhub.ticketsystem.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ResolutionDTO(
//    @field:SerializedName("resolutionId")
//    val resolutionId: Int,
//    @field:SerializedName("resolutionTicket")
//    val resolutionTicket: Int,
    @field:SerializedName("resolutionName")
    val resolutionResolvedBy: String,
    @field:SerializedName("resolutionTime")
    val resolutionResolvedAt: String,
    @field:SerializedName("resolutionContent")
    val resolutionContent: String
)
