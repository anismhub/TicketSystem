package com.anismhub.ticketsystem.utils

import com.anismhub.ticketsystem.presentation.common.InputTextState
import java.time.ZoneId
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
    val jakartaZonedDateTime = zonedDateTime.withZoneSameInstant(ZoneId.of("Asia/Jakarta"))
    val dateFormatter = DateTimeFormatter.ofPattern(dateFormat)
    val timeFormatter = DateTimeFormatter.ofPattern(timeFormat)
    val formattedDate = jakartaZonedDateTime.toLocalDate().format(dateFormatter)
    val formattedTime = jakartaZonedDateTime.toLocalTime().format(timeFormatter)
    return "$formattedDate $formattedTime"
}