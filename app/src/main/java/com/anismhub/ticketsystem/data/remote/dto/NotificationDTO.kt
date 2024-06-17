package com.anismhub.ticketsystem.data.remote.dto

import com.google.gson.annotations.SerializedName

data class NotificationDTO(
    @field:SerializedName("error")
    val error: Boolean,

    @field:SerializedName("status")
    val status: Int,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("data")
    val data: List<NotificationDataDTO>
)

data class NotificationDataDTO(
    @field:SerializedName("notificationId")
    val notificationId: Int,

    @field:SerializedName("notificationTicket")
    val notificationTicket: Int,

    @field:SerializedName("notificationContent")
    val notificationContent: String,

    @field:SerializedName("notificationCreateAt")
    val notificationCreateAt: String
)
