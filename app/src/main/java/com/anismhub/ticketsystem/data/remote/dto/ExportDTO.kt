package com.anismhub.ticketsystem.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ExportDTO(
    @field:SerializedName("error")
    val error: Boolean,

    @field:SerializedName("status")
    val status: Int,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("data")
    val data: ExportDataDTO
)

data class ExportDataDTO(
    @field:SerializedName("downloadUrl")
    val downloadUrl: String,
    val content: List<ExportContentDTO>
)

data class ExportContentDTO(
    @field:SerializedName("ticket_id")
    val ticketId: Int,

    @field:SerializedName("ticket_subject")
    val ticketSubject: String,

    @field:SerializedName("ticket_description")
    val ticketDescription: String,

    @field:SerializedName("ticket_status")
    val ticketStatus: String,

    @field:SerializedName("ticket_priority")
    val ticketPriority: String,

    @field:SerializedName("ticket_area") val
    ticketArea: String,

    @field:SerializedName("ticket_category")
    val ticketCategory: String,

    @field:SerializedName("ticket_created_by")
    val ticketCreateBy: String,

    @field:SerializedName("ticket_department")
    val ticketDepartment: String,

    @field:SerializedName("ticket_create_at")
    val ticketCreateAt: String,

    @field:SerializedName("ticket_update_at")
    val ticketUpdateAt: String
)
