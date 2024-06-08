package com.anismhub.ticketsystem.data.repository

import com.anismhub.ticketsystem.data.mapper.toLogin
import com.anismhub.ticketsystem.data.mapper.toProfile
import com.anismhub.ticketsystem.data.remote.ApiService
import com.anismhub.ticketsystem.domain.manager.LocalDataManager
import com.anismhub.ticketsystem.domain.model.Login
import com.anismhub.ticketsystem.domain.model.LoginData
import com.anismhub.ticketsystem.domain.model.Profile
import com.anismhub.ticketsystem.domain.model.Response
import com.anismhub.ticketsystem.domain.repository.AuthRepository
import com.anismhub.ticketsystem.utils.Resource
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class AuthRespositoryImpl(
    private val apiService: ApiService,
    private val localDataManager: LocalDataManager
) : AuthRepository {
    override fun login(username: String, password: String): Flow<Resource<Login>> = flow {
        emit(Resource.Loading)
        try {
            val response = apiService.login(
                username = username,
                password = password
            )
            emit(Resource.Success(response.toLogin()))
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

    override suspend fun saveLoginData(loginData: LoginData) =
        localDataManager.saveLoginData(loginData = loginData)

    override fun getLoginData(): Flow<LoginData> = localDataManager.getLoginData()

    override fun getLoginState(): Flow<Boolean> = localDataManager.getLoginState()

    override fun getAccessToken(): Flow<String> = localDataManager.getAccessToken()

    override suspend fun clearLoginData() = localDataManager.clearLoginData()

    override fun getProfile(): Flow<Resource<Profile>> = flow {
        emit(Resource.Loading)
        try {
            val response = apiService.profile()
            emit(Resource.Success(response.toProfile()))
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