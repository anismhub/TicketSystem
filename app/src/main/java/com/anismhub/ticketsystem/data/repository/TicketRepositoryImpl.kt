package com.anismhub.ticketsystem.data.repository

import android.content.Context
import android.net.Uri
import com.anismhub.ticketsystem.data.mapper.toDetailTicket
import com.anismhub.ticketsystem.data.mapper.toResponse
import com.anismhub.ticketsystem.data.mapper.toTicket
import com.anismhub.ticketsystem.data.remote.ApiService
import com.anismhub.ticketsystem.domain.model.DetailTicket
import com.anismhub.ticketsystem.domain.model.Response
import com.anismhub.ticketsystem.domain.model.Ticket
import com.anismhub.ticketsystem.domain.repository.TicketRepository
import com.anismhub.ticketsystem.utils.Resource
import com.anismhub.ticketsystem.utils.reduceFileImageAsync
import com.anismhub.ticketsystem.utils.uriToFile
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.Response as retrofitResponse

class TicketRepositoryImpl(
    private val apiService: ApiService,
    private val context: Context
) : TicketRepository {
    override fun exportReport(
        startDate: String,
        endDate: String
    ): Flow<Resource<retrofitResponse<ResponseBody>>> = flow {
        emit(Resource.Loading)
        try {
            val response = apiService.exportTickets(startDate, endDate)
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

    override fun getOnProgressTickets(status: String, search: String?): Flow<Resource<Ticket>> =
        flow {
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
        ticketCategory: Int,
        ticketCode: String
    ): Flow<Resource<Response>> = flow {
        emit(Resource.Loading)
        try {
            val response = apiService.addTicket(
                ticketSubject = ticketSubject,
                ticketDescription = ticketDescription,
                ticketPriority = ticketPriority,
                ticketArea = ticketArea,
                ticketCategory = ticketCategory,
                ticketCode = ticketCode
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

    override fun assignTicket(ticketId: Int, userId: Int, ticketCode: String): Flow<Resource<Response>> = flow {
        emit(Resource.Loading)
        try {
            val response = apiService.assignTicket(ticketId = ticketId, userId = userId, ticketCode = ticketCode)
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

    override fun addComment(ticketId: Int, comment: String, file: Uri?, code: String): Flow<Resource<Response>> =
        flow {
            emit(Resource.Loading)
            try {
                var filePart: MultipartBody.Part? = null
                val requestBody = comment.toRequestBody("text/plain".toMediaType())
                val ticketCode = code.toRequestBody("text/plain".toMediaType())
                file?.let {
                    val imageFile = uriToFile(it, context).reduceFileImageAsync()
                    val fileBody = imageFile.asRequestBody("image/jpeg".toMediaType())
                    filePart = MultipartBody.Part.createFormData("file", imageFile.name, fileBody)
                }
                val response = apiService.addComment(
                    ticketId = ticketId,
                    content = requestBody,
                    file = filePart,
                    ticketCode = ticketCode
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

    override fun closeTicket(ticketId: Int, resolusi: String, ticketCode: String): Flow<Resource<Response>> = flow {
        emit(Resource.Loading)
        try {
            val response = apiService.closeTicket(
                ticketId = ticketId,
                content = resolusi,
                ticketCode = ticketCode
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