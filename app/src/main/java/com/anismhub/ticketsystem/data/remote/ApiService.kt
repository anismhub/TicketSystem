package com.anismhub.ticketsystem.data.remote

import com.anismhub.ticketsystem.data.remote.dto.LoginDTO
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {

    @FormUrlEncoded
    @POST("login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): LoginDTO
}