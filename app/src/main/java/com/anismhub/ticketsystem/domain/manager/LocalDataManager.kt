package com.anismhub.ticketsystem.domain.manager

import com.anismhub.ticketsystem.domain.model.LoginData
import kotlinx.coroutines.flow.Flow

interface LocalDataManager {
    suspend fun saveLoginData(loginData: LoginData)

    fun getLoginData(): Flow<LoginData>

    fun getLoginState(): Flow<Boolean>

    fun getAccessToken(): Flow<String>

    suspend fun clearLoginData()
}