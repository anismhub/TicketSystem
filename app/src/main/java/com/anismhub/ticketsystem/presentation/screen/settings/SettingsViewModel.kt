package com.anismhub.ticketsystem.presentation.screen.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anismhub.ticketsystem.domain.model.LoginData
import com.anismhub.ticketsystem.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {
    private val dummyLoginData = LoginData( 9999,"admin","admin","admin","admin")

    private val _loginData: MutableStateFlow<LoginData> = MutableStateFlow(dummyLoginData)
    val loginData: StateFlow<LoginData> = _loginData


    init {
        getLoginData()
    }

    private fun getLoginData() {
        viewModelScope.launch {
            authRepository.getLoginData().collect {
                _loginData.value = it
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            authRepository.clearLoginData()
        }
    }
}