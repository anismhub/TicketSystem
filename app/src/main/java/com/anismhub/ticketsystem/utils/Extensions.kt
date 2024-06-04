package com.anismhub.ticketsystem.utils

import com.anismhub.ticketsystem.presentation.common.InputTextState

fun InputTextState.isInvalid(): Boolean {
    return this.value.isEmpty() || this.isError
}