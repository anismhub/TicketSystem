package com.anismhub.ticketsystem.data.repository

import com.anismhub.ticketsystem.data.mapper.toDetailTicket
import com.anismhub.ticketsystem.data.mapper.toResponse
import com.anismhub.ticketsystem.data.mapper.toTicket
import com.anismhub.ticketsystem.data.remote.ApiService
import com.anismhub.ticketsystem.domain.model.DetailTicket
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
    override fun getOpenTickets(status: String): Flow<Resource<Ticket>> = flow {
        emit(Resource.Loading)
        try {
            val response = apiService.tickets(status)
            emit(Resource.Success(response.toTicket()))
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

    override fun getOnProgressTickets(status: String): Flow<Resource<Ticket>> = flow {
        emit(Resource.Loading)
        try {
            val response = apiService.tickets(status)
            emit(Resource.Success(response.toTicket()))
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

    override fun getClosedTickets(status: String): Flow<Resource<Ticket>> = flow {
        emit(Resource.Loading)
        try {
            val response = apiService.tickets(status)
            emit(Resource.Success(response.toTicket()))
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

    override fun getTicketById(ticketId: Int): Flow<Resource<DetailTicket>> = flow {
        emit(Resource.Loading)
        try {
            val response = apiService.ticketById(ticketId)
            emit(Resource.Success(response.toDetailTicket()))
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

    override fun addTicket(
        ticketSubject: String,
        ticketDescription: String,
        ticketPriority: Int,
        ticketArea: Int,
        ticketCategory: Int
    ): Flow<Resource<Response>> = flow {
        emit(Resource.Loading)
        try {
            val response = apiService.addTicket(
                ticketSubject = ticketSubject,
                ticketDescription = ticketDescription,
                ticketPriority = ticketPriority,
                ticketArea = ticketArea,
                ticketCategory = ticketCategory
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
}