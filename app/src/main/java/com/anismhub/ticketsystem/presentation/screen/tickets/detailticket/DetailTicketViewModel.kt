package com.anismhub.ticketsystem.presentation.screen.tickets.detailticket

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anismhub.ticketsystem.domain.model.DetailTicket
import com.anismhub.ticketsystem.domain.model.ProfileData
import com.anismhub.ticketsystem.domain.model.Response
import com.anismhub.ticketsystem.domain.model.TechProfile
import com.anismhub.ticketsystem.domain.repository.AuthRepository
import com.anismhub.ticketsystem.domain.repository.TicketRepository
import com.anismhub.ticketsystem.utils.Event
import com.anismhub.ticketsystem.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailTicketViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val ticketRepository: TicketRepository
) : ViewModel() {
    private val _detailTicket = MutableStateFlow<Resource<DetailTicket>>(Resource.None)
    val detailTicket: StateFlow<Resource<DetailTicket>> = _detailTicket

    private val _assignTicket = MutableStateFlow<Event<Resource<Response>>>(Event(Resource.None))
    val assignTicket: StateFlow<Event<Resource<Response>>> = _assignTicket

    private val _comments = MutableStateFlow<Event<Resource<Response>>>(Event(Resource.None))
    val comments: StateFlow<Event<Resource<Response>>> = _comments

    private val _closeTicket = MutableStateFlow<Event<Resource<Response>>>(Event(Resource.None))
    val closeTicket: StateFlow<Event<Resource<Response>>> = _closeTicket

    private val _techUsers = MutableStateFlow<Resource<TechProfile>>(Resource.None)
    val techUsers: StateFlow<Resource<TechProfile>> = _techUsers

    private val _localProfileData = mutableStateOf<ProfileData?>(null)
    val localProfileData: State<ProfileData?> = _localProfileData

    init {
        getTechUsers()
        getLocalProfile()
    }

    private fun getLocalProfile() {
        viewModelScope.launch {
            authRepository.getProfileData().collect {
                _localProfileData.value = it
            }
        }
    }

    private fun getTechUsers() {
        viewModelScope.launch {
            authRepository.getTechUsers().collect {
                _techUsers.value = it
            }
        }
    }

    fun getTicketById(ticketId: Int) {
        viewModelScope.launch {
            ticketRepository.getTicketById(ticketId).collect {
                _detailTicket.value = it
            }
        }
    }

    fun assignTicket(ticketId: Int, userId: Int) {
        viewModelScope.launch {
            ticketRepository.assignTicket(ticketId, userId).collect {
                _assignTicket.value = Event(it)
            }
        }
    }

    fun addComment(ticketId: Int, comment: String) {
        viewModelScope.launch {
            ticketRepository.addComment(ticketId, comment).collect {
                _comments.value = Event(it)
            }
        }
    }

    fun closeTicket(ticketId: Int, resolusi: String) {
        viewModelScope.launch {
            ticketRepository.closeTicket(ticketId, resolusi).collect {
                _closeTicket.value = Event(it)
            }
        }
    }
}