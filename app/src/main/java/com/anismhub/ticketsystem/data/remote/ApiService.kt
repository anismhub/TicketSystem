package com.anismhub.ticketsystem.data.remote

import com.anismhub.ticketsystem.data.remote.dto.DetailTicketDTO
import com.anismhub.ticketsystem.data.remote.dto.LoginDTO
import com.anismhub.ticketsystem.data.remote.dto.ProfileDTO
import com.anismhub.ticketsystem.data.remote.dto.ResponseDTO
import com.anismhub.ticketsystem.data.remote.dto.TechProfileDTO
import com.anismhub.ticketsystem.data.remote.dto.TicketDTO
import retrofit2.Response
import okhttp3.ResponseBody
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Streaming

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
    ): TicketDTO

    @GET("users/tech")
    suspend fun getTechs(): TechProfileDTO

    @FormUrlEncoded
    @POST("tickets")
    suspend fun addTicket(
        @Field("ticketSubject") ticketSubject: String,
        @Field("ticketDescription") ticketDescription: String,
        @Field("ticketPriority") ticketPriority: String,
        @Field("ticketArea") ticketArea: Int,
        @Field("ticketCategory") ticketCategory: Int
    ): ResponseDTO

    @Streaming
    @GET("tickets/export")
    suspend fun exportTickets(): Response<ResponseBody>

    @GET("tickets/{ticketId}")
    suspend fun ticketById(
        @Path("ticketId") ticketId: Int
    ): DetailTicketDTO

    @FormUrlEncoded
    @POST("tickets/{ticketId}/assign")
    suspend fun assignTicket(
        @Path("ticketId") ticketId: Int,
        @Field("userId") userId: Int
    ): ResponseDTO

    @FormUrlEncoded
    @POST("tickets/{ticketId}/comments")
    suspend fun addComment(
        @Path("ticketId") ticketId: Int,
        @Field("content") content: String,
    ): ResponseDTO

    @FormUrlEncoded
    @POST("tickets/{ticketId}/close")
    suspend fun closeTicket(
        @Path("ticketId") ticketId: Int,
        @Field("content") content: String,
    ): ResponseDTO

}