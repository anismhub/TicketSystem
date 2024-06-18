package com.anismhub.ticketsystem.domain.repository

import com.anismhub.ticketsystem.domain.model.Login
import com.anismhub.ticketsystem.domain.model.LoginData
import com.anismhub.ticketsystem.domain.model.Notification
import com.anismhub.ticketsystem.domain.model.Profile
import com.anismhub.ticketsystem.domain.model.ProfileData
import com.anismhub.ticketsystem.domain.model.Response
import com.anismhub.ticketsystem.domain.model.TechProfile
import com.anismhub.ticketsystem.domain.model.Users
import com.anismhub.ticketsystem.utils.Resource
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun login(username: String, password: String): Flow<Resource<Login>>

    suspend fun saveLoginData(loginData: LoginData)

    fun getLoginData(): Flow<LoginData>

    fun getLoginState(): Flow<Boolean>

    fun getAccessToken(): Flow<String>

    suspend fun clearData()

    fun addUser(
        username: String,
        fullname: String,
        password: String,
        role: String,
        department: Int,
        phoneNumber: String
    ): Flow<Resource<Response>>

    fun getUsers(search: String? = null): Flow<Resource<Users>>
    fun getUserById(userId: Int): Flow<Resource<Profile>>
    fun deleteUser(userId: Int): Flow<Resource<Response>>
    fun postEditUser(
        userId: Int,
        username: String,
        fullname: String,
        role: String,
        department: Int,
        phoneNumber: String
    ): Flow<Resource<Response>>

    fun postChangePassword(userId: Int, password: String): Flow<Resource<Response>>
    fun getProfile(): Flow<Resource<Profile>>

    fun getProfileData(): Flow<ProfileData>

    fun getTechUsers(): Flow<Resource<TechProfile>>

    suspend fun saveProfileData(profileData: ProfileData)

    suspend fun updateFCMToken(token: String)

    suspend fun deleteFCMToken()

    fun getNotification(): Flow<Resource<Notification>>
}