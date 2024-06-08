package com.anismhub.ticketsystem.data.repository

import com.anismhub.ticketsystem.data.remote.ApiService
import com.anismhub.ticketsystem.domain.model.Response
import com.anismhub.ticketsystem.domain.model.Ticket
import com.anismhub.ticketsystem.domain.repository.TicketRepository
import com.anismhub.ticketsystem.utils.Resource
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class TicketRepositoryImpl(
    private val apiService: ApiService
) : TicketRepository {
    override fun getTickets(status: String): Flow<Resource<Ticket>> = flow {
        emit(Resource.Loading)
        try {
            val response = apiService.tickets(status)
            emit(Resource.Success(response))
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