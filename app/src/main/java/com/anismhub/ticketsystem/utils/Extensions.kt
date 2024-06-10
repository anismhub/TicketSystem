package com.anismhub.ticketsystem.utils

import com.anismhub.ticketsystem.presentation.common.InputTextState
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

fun InputTextState.isInvalid(): Boolean {
    return this.value.isEmpty() || this.isError
}

fun String.toDateTime(
    dateFormat: String = "dd/MM/yyyy",
    timeFormat: String = "HH:mm:ss"
): String {
    val zonedDateTime = ZonedDateTime.parse(this, DateTimeFormatter.ISO_DATE_TIME)
    val dateFormatter = DateTimeFormatter.ofPattern(dateFormat)
    val timeFormatter = DateTimeFormatter.ofPattern(timeFormat)
    val formattedDate = zonedDateTime.toLocalDate().format(dateFormatter)
    val formattedTime = zonedDateTime.toLocalTime().format(timeFormatter)
    return "$formattedDate $formattedTime"
}