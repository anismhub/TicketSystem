package com.anismhub.ticketsystem.domain

data class Ticket(
    val title: String,
    val description: String,
    val priority: String,
    val status: String,
    val date: String
)
