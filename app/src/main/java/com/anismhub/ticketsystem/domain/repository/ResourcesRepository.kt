package com.anismhub.ticketsystem.domain.repository

import com.anismhub.ticketsystem.domain.model.Departments
import com.anismhub.ticketsystem.utils.Resource
import kotlinx.coroutines.flow.Flow

interface ResourcesRepository {
    fun getDepartments(): Flow<Resource<Departments>>
}