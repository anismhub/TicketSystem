package com.anismhub.ticketsystem.data.repository

import com.anismhub.ticketsystem.data.remote.ApiService
import com.anismhub.ticketsystem.domain.model.Response
import com.anismhub.ticketsystem.domain.model.Ticket
import com.anismhub.ticketsystem.domain.repository.TicketRepository
import com.anismhub.ticketsystem.utils.Result
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class TicketRepositoryImpl(
    private val apiService: ApiService
) : TicketRepository {
    override fun getTickets(status: String): Flow<Result<Ticket>> = flow {
        emit(Result.Loading)
        try {
            val response = apiService.tickets(status)
            emit(Result.Success(response))
        } catch (e: Exception) {
            if (e is HttpException) {
                val jsonInString = e.response()?.errorBody()?.string()
                val errorBody = Gson().fromJson(jsonInString, Response::class.java)
                emit(Result.Error(errorBody.message))
            } else {
                emit(Result.Error(e.message.toString()))
            }
        }
    }
}