package com.anismhub.ticketsystem.presentation.screen.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anismhub.ticketsystem.domain.model.Login
import com.anismhub.ticketsystem.domain.model.LoginData
import com.anismhub.ticketsystem.domain.repository.AuthRepository
import com.anismhub.ticketsystem.utils.Event
import com.anismhub.ticketsystem.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {
    private val _loginResource: MutableStateFlow<Event<Resource<Login>>> =
        MutableStateFlow(Event(Resource.None))
    val loginResource: StateFlow<Event<Resource<Login>>> = _loginResource

    private val _loginState: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val loginState: StateFlow<Boolean> = _loginState

    init {
        getLoginState()
    }

    fun login(username: String, password: String) {
        viewModelScope.launch {
            authRepository.login(
                username = username,
                password = password
            ).collect {
                _loginResource.value = Event(it)
            }
        }
    }

    fun saveLoginData(loginData: LoginData) {
        viewModelScope.launch {
            authRepository.saveLoginData(loginData = loginData)
        }
        getLoginState()
    }

    private fun getLoginState() {
        viewModelScope.launch {
            authRepository.getLoginState().collect {
                _loginState.value = it
            }
        }
    }
}