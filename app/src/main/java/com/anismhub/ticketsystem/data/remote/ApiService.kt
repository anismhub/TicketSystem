package com.anismhub.ticketsystem.data.remote

import com.anismhub.ticketsystem.data.remote.dto.LoginDTO
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {

    @FormUrlEncoded
    @POST("users/auth")
    suspend fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): LoginDTO
}