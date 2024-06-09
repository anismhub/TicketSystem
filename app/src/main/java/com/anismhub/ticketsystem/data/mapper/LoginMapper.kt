package com.anismhub.ticketsystem.data.mapper

import com.anismhub.ticketsystem.data.remote.dto.LoginDTO
import com.anismhub.ticketsystem.data.remote.dto.LoginDataDTO
import com.anismhub.ticketsystem.data.remote.dto.ProfileDTO
import com.anismhub.ticketsystem.data.remote.dto.ProfileDataDto
import com.anismhub.ticketsystem.domain.model.Login
import com.anismhub.ticketsystem.domain.model.LoginData
import com.anismhub.ticketsystem.domain.model.Profile
import com.anismhub.ticketsystem.domain.model.ProfileData

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

fun ProfileDataDto.toProfileData(): ProfileData {
    return ProfileData(
        userId = userId,
        userName = userName,
        userFullName = userFullName,
        userRole = userRole,
        departmentId = departmentId,
        departmentName = departmentName,
        userPhone = userPhone,
    )
}

fun ProfileDTO.toProfile(): Profile {
    return Profile(
        error = error,
        status = status,
        message = message,
        data = data.toProfileData()
    )
}