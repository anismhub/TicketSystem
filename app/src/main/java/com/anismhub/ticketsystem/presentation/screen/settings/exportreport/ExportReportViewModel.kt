package com.anismhub.ticketsystem.presentation.screen.settings.exportreport

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anismhub.ticketsystem.domain.repository.AuthRepository
import com.anismhub.ticketsystem.utils.Event
import com.anismhub.ticketsystem.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ExportReportViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {
    private val _accessToken: MutableState<String> = mutableStateOf("")
    val accessToken: State<String> = _accessToken

    private val _exportReport: MutableStateFlow<Event<Resource<Response<ResponseBody>>>> =
        MutableStateFlow(Event(Resource.None))
    val exportReport: StateFlow<Event<Resource<Response<ResponseBody>>>> = _exportReport

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
}
