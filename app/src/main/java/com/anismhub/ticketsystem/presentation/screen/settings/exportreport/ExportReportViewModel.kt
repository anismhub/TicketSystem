package com.anismhub.ticketsystem.presentation.screen.settings.exportreport

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anismhub.ticketsystem.domain.model.Export
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
class ExportReportViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val ticketRepository: TicketRepository
) : ViewModel() {
    private val _accessToken: MutableState<String> = mutableStateOf("")
    val accessToken: State<String> = _accessToken

    private val _exportReport: MutableStateFlow<Event<Resource<Export>>> =
        MutableStateFlow(Event(Resource.None))
    val exportReport: StateFlow<Event<Resource<Export>>> = _exportReport

    init {
        getAccessToken()
    }

    private fun getAccessToken() {
        viewModelScope.launch {
            authRepository.getAccessToken().collect {
                _accessToken.value = it
            }
        }
    }

    fun getExportReport(
        startDate: String,
        endDate: String
    ) {
        viewModelScope.launch {
            ticketRepository.exportReport(
                startDate = startDate,
                endDate = endDate
            ).collect {
                _exportReport.value = Event(it)
            }
        }
    }
}
