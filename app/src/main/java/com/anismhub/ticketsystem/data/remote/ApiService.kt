package com.anismhub.ticketsystem.data.remote

import com.anismhub.ticketsystem.data.remote.dto.DepartmentsDTO
import com.anismhub.ticketsystem.data.remote.dto.DetailTicketDTO
import com.anismhub.ticketsystem.data.remote.dto.LoginDTO
import com.anismhub.ticketsystem.data.remote.dto.NotificationDTO
import com.anismhub.ticketsystem.data.remote.dto.ProfileDTO
import com.anismhub.ticketsystem.data.remote.dto.ResponseDTO
import com.anismhub.ticketsystem.data.remote.dto.TechProfileDTO
import com.anismhub.ticketsystem.data.remote.dto.TicketDTO
import com.anismhub.ticketsystem.data.remote.dto.UsersDTO
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap
import retrofit2.http.Streaming

interface ApiService {

    @FormUrlEncoded
    @POST("users/auth")
    suspend fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): LoginDTO

    @GET("users")
    suspend fun getUsers(
        @Query("search") search: String?
    ): UsersDTO

    @GET("resources/department")
    suspend fun getDepartments(): DepartmentsDTO

    @GET("users/{userId}")
    suspend fun getUserById(
        @Path("userId") userId: Int
    ): ProfileDTO

    @FormUrlEncoded
    @POST("users/{userId}")
    suspend fun editUser(
        @Path("userId") userId: Int,
        @Field("username") username: String,
        @Field("fullname") fullname: String,
        @Field("role") role: String,
        @Field("department") department: Int,
        @Field("phoneNumber") phoneNumber: String
    ): ResponseDTO

    @DELETE("users/{userId}")
    suspend fun deleteUser(
        @Path("userId") userId: Int
    ): ResponseDTO

    @FormUrlEncoded
    @POST("users/password")
    suspend fun changePassword(
        @Field("currentPassword") currentPassword: String,
        @Field("newPassword") newPassword: String
    ): ResponseDTO

    @FormUrlEncoded
    @POST("users")
    suspend fun addUser(
        @Field("username") username: String,
        @Field("fullname") fullname: String,
        @Field("password") password: String,
        @Field("role") role: String,
        @Field("department") department: Int,
        @Field("phoneNumber") phoneNumber: String
    ): ResponseDTO

    @GET("users/profile")
    suspend fun getProfile(): ProfileDTO

    @GET("tickets")
    suspend fun getTickets(
        @QueryMap queries: Map<String, String>
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
        @Field("ticketCategory") ticketCategory: Int,
        @Field("ticketCode") ticketCode: String
    ): ResponseDTO

    @Streaming
    @GET("tickets/export")
    suspend fun exportTickets(
        @Query("startDate") startDate: String,
        @Query("endDate") endDate: String
    ): Response<ResponseBody>

    @GET("tickets/{ticketId}")
    suspend fun ticketById(
        @Path("ticketId") ticketId: Int
    ): DetailTicketDTO

    @FormUrlEncoded
    @POST("tickets/{ticketId}/assign")
    suspend fun assignTicket(
        @Path("ticketId") ticketId: Int,
        @Field("userId") userId: Int,
        @Field("ticketCode") ticketCode: String
    ): ResponseDTO

    @Multipart
    @POST("tickets/{ticketId}/comments")
    suspend fun addComment(
        @Path("ticketId") ticketId: Int,
        @Part("content") content: RequestBody,
        @Part file: MultipartBody.Part? = null,
        @Part("ticketCode") ticketCode: RequestBody
    ): ResponseDTO

    @FormUrlEncoded
    @POST("tickets/{ticketId}/close")
    suspend fun closeTicket(
        @Path("ticketId") ticketId: Int,
        @Field("ticketCode") ticketCode: String
    ): ResponseDTO

    @FormUrlEncoded
    @POST("users/token")
    suspend fun refreshToken(
        @Field("deviceId") deviceId: String,
        @Field("token") token: String
    )

    @FormUrlEncoded
    @PUT("users/token")
    suspend fun deleteToken(
        @Field("deviceId") deviceId: String
    )

    @GET("users/notification")
    suspend fun getNotification(): NotificationDTO
}