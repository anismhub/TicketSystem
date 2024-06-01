package com.anismhub.ticketsystem.data.mapper

import com.anismhub.ticketsystem.data.remote.dto.LoginDTO
import com.anismhub.ticketsystem.data.remote.dto.LoginDataDTO
import com.anismhub.ticketsystem.domain.model.Login
import com.anismhub.ticketsystem.domain.model.LoginData

fun LoginDataDTO.toLoginData(): LoginData {
    return LoginData(
        userId = userId,
        userName = userName,
        userFullName = userFullName,
        userRole = userRole,
        accessToken = accessToken
    )
}

fun LoginDTO.toLogin(): Login {
    return Login(
        error = error,
        status = status,
        message = message,
        data = data.toLoginData()
    )
}