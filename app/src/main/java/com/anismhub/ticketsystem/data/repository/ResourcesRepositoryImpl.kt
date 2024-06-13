package com.anismhub.ticketsystem.data.repository

import com.anismhub.ticketsystem.data.mapper.toDepartments
import com.anismhub.ticketsystem.data.remote.ApiService
import com.anismhub.ticketsystem.domain.model.Departments
import com.anismhub.ticketsystem.domain.model.Response
import com.anismhub.ticketsystem.domain.repository.ResourcesRepository
import com.anismhub.ticketsystem.utils.Resource
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class ResourcesRepositoryImpl(
    private val apiService: ApiService
) : ResourcesRepository {

    override fun getDepartments(): Flow<Resource<Departments>> = flow {
        emit(Resource.Loading)
        try {
            val response = apiService.getDepartments()
            emit(Resource.Success(response.toDepartments()))
        } catch (e: Exception) {
            if (e is HttpException) {
                val jsonInString = e.response()?.errorBody()?.string()
                val errorBody = Gson().fromJson(jsonInString, Response::class.java)
                emit(Resource.Error(errorBody.message))
            } else {
                emit(Resource.Error(e.message.toString()))
            }
        }
    }
}