package com.anismhub.ticketsystem.domain.repository

import com.anismhub.ticketsystem.domain.model.Login
import com.anismhub.ticketsystem.domain.model.LoginData
import kotlinx.coroutines.flow.Flow
import com.anismhub.ticketsystem.utils.Result

interface AuthRepository {
    fun login(username: String, password: String): Flow<Result<Login>>

    suspend fun saveLoginData(loginData: LoginData)
}