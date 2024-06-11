package com.anismhub.ticketsystem.domain.repository

import com.anismhub.ticketsystem.domain.model.Login
import com.anismhub.ticketsystem.domain.model.LoginData
import com.anismhub.ticketsystem.domain.model.Profile
import com.anismhub.ticketsystem.domain.model.ProfileData
import com.anismhub.ticketsystem.domain.model.TechProfile
import com.anismhub.ticketsystem.domain.model.TechProfileData
import kotlinx.coroutines.flow.Flow
import com.anismhub.ticketsystem.utils.Resource

interface AuthRepository {
    fun login(username: String, password: String): Flow<Resource<Login>>

    suspend fun saveLoginData(loginData: LoginData)

    fun getLoginData(): Flow<LoginData>

    fun getLoginState(): Flow<Boolean>

    fun getAccessToken(): Flow<String>

    suspend fun clearData()

    fun getProfile(): Flow<Resource<Profile>>

    fun getProfileData(): Flow<ProfileData>

    fun getTechUsers(): Flow<Resource<TechProfile>>

    suspend fun saveProfileData(profileData: ProfileData)
}