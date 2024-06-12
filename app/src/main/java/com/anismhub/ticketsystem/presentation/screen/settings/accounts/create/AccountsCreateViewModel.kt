package com.anismhub.ticketsystem.presentation.screen.settings.accounts.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anismhub.ticketsystem.domain.model.Response
import com.anismhub.ticketsystem.domain.repository.AuthRepository
import com.anismhub.ticketsystem.utils.Event
import com.anismhub.ticketsystem.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountsCreateViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {
    private val _addUser: MutableStateFlow<Event<Resource<Response>>> =
        MutableStateFlow(Event(Resource.None))
    val addUser: StateFlow<Event<Resource<Response>>> = _addUser

    fun addUser(
        username: String,
        fullname: String,
        password: String,
        role: String,
        department: Int,
        phoneNumber: String
    ) {
        viewModelScope.launch {
            authRepository.addUser(username, fullname, password, role, department, phoneNumber).collect {
                _addUser.value = Event(it)
            }
        }
    }

}