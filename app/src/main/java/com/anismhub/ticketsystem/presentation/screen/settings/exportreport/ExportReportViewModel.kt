package com.anismhub.ticketsystem.presentation.screen.settings.exportreport

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anismhub.ticketsystem.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExportReportViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {
    private val _accessToken: MutableState<String> = mutableStateOf("")
    val accessToken: State<String> = _accessToken

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
