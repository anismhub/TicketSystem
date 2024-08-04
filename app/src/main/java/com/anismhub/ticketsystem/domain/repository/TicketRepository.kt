package com.anismhub.ticketsystem.domain.repository

import android.net.Uri
import com.anismhub.ticketsystem.domain.model.DetailTicket
import com.anismhub.ticketsystem.domain.model.Response
import com.anismhub.ticketsystem.domain.model.Ticket
import com.anismhub.ticketsystem.utils.Resource
import kotlinx.coroutines.flow.Flow
import okhttp3.ResponseBody
import retrofit2.Response as RetrofitResponse

interface TicketRepository {
    fun exportReport(
        startDate: String,
        endDate: String
    ): Flow<Resource<RetrofitResponse<ResponseBody>>>

    fun getOpenTickets(status: String, search: String? = null): Flow<Resource<Ticket>>
    fun getOnProgressTickets(status: String, search: String? = null): Flow<Resource<Ticket>>
    fun getClosedTickets(status: String, search: String? = null): Flow<Resource<Ticket>>

    fun getTicketById(ticketId: Int): Flow<Resource<DetailTicket>>

    fun addTicket(
        ticketSubject: String,
        ticketDescription: String,
        ticketPriority: String,
        ticketArea: Int,
        ticketCategory: Int,
        ticketCode: String
    ): Flow<Resource<Response>>

    fun assignTicket(
        ticketId: Int,
        userId: Int,
        ticketCode: String
    ): Flow<Resource<Response>>

    fun addComment(
        ticketId: Int,
        comment: String,
        file: Uri? = null,
        code: String
    ): Flow<Resource<Response>>

    fun closeTicket(
        ticketId: Int,
        ticketCode: String
    ): Flow<Resource<Response>>
}