package com.anismhub.ticketsystem.domain.repository

import com.anismhub.ticketsystem.domain.model.Login
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun login(username: String, password: String): Flow<Result<Login>>
}