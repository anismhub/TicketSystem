package com.anismhub.ticketsystem.data.remote

import com.anismhub.ticketsystem.data.remote.dto.LoginDTO
import com.anismhub.ticketsystem.data.remote.dto.ProfileDTO
import com.anismhub.ticketsystem.data.remote.dto.ResponseDTO
import com.anismhub.ticketsystem.domain.model.Response
import com.anismhub.ticketsystem.domain.model.Ticket
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @FormUrlEncoded
    @POST("users/auth")
    suspend fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): LoginDTO

    @GET("users/profile")
    suspend fun profile(): ProfileDTO

    @GET("tickets")
    suspend fun tickets(
        @Query("status") status: String
    ): Ticket

    @FormUrlEncoded
    @POST("tickets")
    suspend fun addTicket(
        @Field("ticketSubject") ticketSubject: String,
        @Field("ticketDescription") ticketDescription: String,
        @Field("ticketPriority") ticketPriority: Int,
        @Field("ticketArea") ticketArea: Int,
        @Field("ticketCategory") ticketCategory: Int
    ): ResponseDTO
}