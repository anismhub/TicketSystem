package com.anismhub.ticketsystem.data.repository

import com.anismhub.ticketsystem.data.mapper.toLogin
import com.anismhub.ticketsystem.data.mapper.toProfile
import com.anismhub.ticketsystem.data.mapper.toResponse
import com.anismhub.ticketsystem.data.mapper.toTechProfile
import com.anismhub.ticketsystem.data.mapper.toUsers
import com.anismhub.ticketsystem.data.remote.ApiService
import com.anismhub.ticketsystem.domain.manager.LocalDataManager
import com.anismhub.ticketsystem.domain.model.Login
import com.anismhub.ticketsystem.domain.model.LoginData
import com.anismhub.ticketsystem.domain.model.Profile
import com.anismhub.ticketsystem.domain.model.ProfileData
import com.anismhub.ticketsystem.domain.model.Response
import com.anismhub.ticketsystem.domain.model.TechProfile
import com.anismhub.ticketsystem.domain.model.Users
import com.anismhub.ticketsystem.domain.repository.AuthRepository
import com.anismhub.ticketsystem.utils.Resource
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class AuthRepositoryImpl(
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

    override suspend fun clearData() = localDataManager.clearData()
    override fun addUser(
        username: String,
        fullname: String,
        password: String,
        role: String,
        department: Int,
        phoneNumber: String
    ): Flow<Resource<Response>> = flow {
        emit(Resource.Loading)
        try {
            val response = apiService.addUser(
                username = username,
                fullname = fullname,
                password = password,
                role = role,
                department = department,
                phoneNumber = phoneNumber
            )
            emit(Resource.Success(response.toResponse()))
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

    override fun getUsers(): Flow<Resource<Users>> = flow {
        emit(Resource.Loading)
        try {
            val response = apiService.getUsers()
            emit(Resource.Success(response.toUsers()))
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

    override fun getUserById(userId: Int): Flow<Resource<Profile>> = flow {
        emit(Resource.Loading)
        try {
            val response = apiService.getUserById(userId)
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

    override fun postEditUser(
        userId: Int,
        username: String,
        fullname: String,
        role: String,
        department: Int,
        phoneNumber: String
    ): Flow<Resource<Response>> = flow {
        emit(Resource.Loading)
        try {
            val response = apiService.editUser(
                userId = userId,
                username = username,
                fullname = fullname,
                role = role,
                department = department,
                phoneNumber = phoneNumber
            )
            emit(Resource.Success(response.toResponse()))
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

    override fun postChangePassword(userId: Int, password: String): Flow<Resource<Response>> = flow {
        emit(Resource.Loading)
        try {
            val response = apiService.changePassword(userId = userId, password = password)
            emit(Resource.Success(response.toResponse()))
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

    override fun getProfile(): Flow<Resource<Profile>> = flow {
        emit(Resource.Loading)
        try {
            val response = apiService.getProfile()
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

    override fun getProfileData(): Flow<ProfileData> = localDataManager.getProfileData()
    override fun getTechUsers(): Flow<Resource<TechProfile>> = flow {
        emit(Resource.Loading)
        try {
            val response = apiService.getTechs()
            emit(Resource.Success(response.toTechProfile()))
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

    override suspend fun saveProfileData(profileData: ProfileData) {
        localDataManager.saveProfileData(profileData)
    }
}