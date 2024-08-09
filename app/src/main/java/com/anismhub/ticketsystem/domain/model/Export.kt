package com.anismhub.ticketsystem.domain.model

data class Export(
    val error: Boolean,
    val status: Int,
    val message: String,
    val data: ExportData
)

data class ExportData(
    val downloadUrl: String,
    val content: List<ExportContent>
)

data class ExportContent(
    val ticketId: Int,
    val ticketSubject: String,
    val ticketDescription: String,
    val ticketStatus: String,
    val ticketPriority: String,
    val ticketArea: String,
    val ticketCategory: String,
    val ticketCreateBy: String,
    val ticketDepartment: String,
    val ticketCreateAt: String,
    val ticketUpdateAt: String
)