package com.anismhub.ticketsystem.domain.mapper

import com.anismhub.ticketsystem.data.remote.dto.ExportContentDTO
import com.anismhub.ticketsystem.data.remote.dto.ExportDTO
import com.anismhub.ticketsystem.data.remote.dto.ExportDataDTO
import com.anismhub.ticketsystem.domain.model.Export
import com.anismhub.ticketsystem.domain.model.ExportContent
import com.anismhub.ticketsystem.domain.model.ExportData

fun ExportDTO.toExport(): Export {
    return Export(
        error = this.error,
        status = this.status,
        message = this.message,
        data = this.data.toExportData()
    )
}

fun ExportDataDTO.toExportData(): ExportData {
    return ExportData(
        downloadUrl = this.downloadUrl,
        content = this.content.map { it.toExportContent() }
    )
}

fun ExportContentDTO.toExportContent(): ExportContent {
    return ExportContent(
        ticketId = this.ticketId,
        ticketSubject = this.ticketSubject,
        ticketDescription = this.ticketDescription,
        ticketStatus = this.ticketStatus,
        ticketPriority = this.ticketPriority,
        ticketArea = this.ticketArea,
        ticketCategory = this.ticketCategory,
        ticketCreateBy = this.ticketCreateBy,
        ticketDepartment = this.ticketDepartment,
        ticketCreateAt = this.ticketCreateAt,
        ticketUpdateAt = this.ticketUpdateAt
    )
}