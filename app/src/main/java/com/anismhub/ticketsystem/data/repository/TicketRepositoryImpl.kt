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
import okhttp3.ResponseBody
import retrofit2.HttpException

class TicketRepositoryImpl(
    private val apiService: ApiService
) : TicketRepository {
    override fun exportReport(): Flow<Resource<retrofit2.Response<ResponseBody>>> = flow {
        emit(Resource.Loading)
        try {
            val response = apiService.exportTickets()
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

    override fun getOpenTickets(status: String, search: String?): Flow<Resource<Ticket>> = flow {
        emit(Resource.Loading)
        try {
            val options = mutableMapOf<String, String>()
            options["status"] = status
            if (!search.isNullOrEmpty()) {
                search.let {
                    options["search"] = it
                }
            }
            val response = apiService.getTickets(options)
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

    override fun getOnProgressTickets(status: String, search: String?): Flow<Resource<Ticket>> = flow {
        emit(Resource.Loading)
        try {
            val options = mutableMapOf<String, String>()
            options["status"] = status
            if (!search.isNullOrEmpty()) {
                search.let {
                    options["search"] = it
                }
            }
            val response = apiService.getTickets(options)
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

    override fun getClosedTickets(status: String, search: String?): Flow<Resource<Ticket>> = flow {
        emit(Resource.Loading)
        try {
            val options = mutableMapOf<String, String>()
            options["status"] = status
            if (!search.isNullOrEmpty()) {
                search.let {
                    options["search"] = it
                }
            }
            val response = apiService.getTickets(options)
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
        ticketPriority: String,
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

    override fun assignTicket(ticketId: Int, userId: Int): Flow<Resource<Response>> = flow {
        emit(Resource.Loading)
        try {
            val response = apiService.assignTicket(ticketId = ticketId, userId = userId)
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

    override fun addComment(ticketId: Int, comment: String): Flow<Resource<Response>> = flow {
        emit(Resource.Loading)
        try {
            val response = apiService.addComment(
                ticketId = ticketId,
                content = comment
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

    override fun closeTicket(ticketId: Int, resolusi: String): Flow<Resource<Response>> = flow {
        emit(Resource.Loading)
        try {
            val response = apiService.closeTicket(
                ticketId = ticketId,
                content = resolusi
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