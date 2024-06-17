package com.anismhub.ticketsystem.domain.model

data class Notification(
    val error: Boolean,
    val status: Int,
    val message: String,
    val data: List<NotificationData>
)

data class NotificationData(
    val notificationId: Int,
    val notificationTicket: Int,
    val notificationContent: String,
    val notificationCreateAt: String
)