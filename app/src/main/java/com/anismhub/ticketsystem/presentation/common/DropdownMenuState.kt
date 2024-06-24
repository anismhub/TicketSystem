package com.anismhub.ticketsystem.presentation.common

data class DropdownMenuState(
    val text: String,
    val indexValue: Int,
    val isError: Boolean = false
)
