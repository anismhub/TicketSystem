package com.anismhub.ticketsystem.data.mapper

import com.anismhub.ticketsystem.data.remote.dto.LoginDTO
import com.anismhub.ticketsystem.data.remote.dto.LoginDataDTO
import com.anismhub.ticketsystem.data.remote.dto.NotificationDTO
import com.anismhub.ticketsystem.data.remote.dto.NotificationDataDTO
import com.anismhub.ticketsystem.data.remote.dto.ProfileDTO
import com.anismhub.ticketsystem.data.remote.dto.ProfileDataDTO
import com.anismhub.ticketsystem.data.remote.dto.TechProfileDTO
import com.anismhub.ticketsystem.data.remote.dto.TechProfileDataDTO
import com.anismhub.ticketsystem.data.remote.dto.UsersDTO
import com.anismhub.ticketsystem.domain.model.Login
import com.anismhub.ticketsystem.domain.model.LoginData
import com.anismhub.ticketsystem.domain.model.Notification
import com.anismhub.ticketsystem.domain.model.NotificationData
import com.anismhub.ticketsystem.domain.model.Profile
import com.anismhub.ticketsystem.domain.model.ProfileData
import com.anismhub.ticketsystem.domain.model.TechProfile
import com.anismhub.ticketsystem.domain.model.TechProfileData
import com.anismhub.ticketsystem.domain.model.Users

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

fun ProfileDataDTO.toProfileData(): ProfileData {
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

fun UsersDTO.toUsers(): Users {
    return Users(
        error = error,
        status = status,
        message = message,
        data = data.map { it.toProfileData() }
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

fun TechProfileDataDTO.toTechProfileData(): TechProfileData {
    return TechProfileData(
        userId = userId,
        userFullName = userFullName,
    )
}
fun TechProfileDTO.toTechProfile(): TechProfile {
    return TechProfile(
        error = error,
        status = status,
        message = message,
        data = data.map { it.toTechProfileData() }
    )
}

fun NotificationDTO.toNotification(): Notification {
    return Notification(
        error = error,
        status = status,
        message = message,
        data = data.map { it.toNotificationData() }
    )
}

fun NotificationDataDTO.toNotificationData(): NotificationData {
    return NotificationData(
        notificationId = notificationId,
        notificationTicket = notificationTicket,
        notificationContent = notificationContent,
        notificationCreateAt = notificationCreateAt,
        notificationTicketCode = notificationTicketCode
    )
}