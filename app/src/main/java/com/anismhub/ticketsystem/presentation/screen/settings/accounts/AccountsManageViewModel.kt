package com.anismhub.ticketsystem.presentation.screen.settings.accounts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anismhub.ticketsystem.domain.model.Response
import com.anismhub.ticketsystem.domain.model.Users
import com.anismhub.ticketsystem.domain.repository.AuthRepository
import com.anismhub.ticketsystem.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountsManageViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _usersData = MutableStateFlow<Resource<Users>>(Resource.None)
    val usersData: StateFlow<Resource<Users>> = _usersData

    private val _deleteUser = MutableStateFlow<Resource<Response>>(Resource.None)
    val deleteUser: StateFlow<Resource<Response>> = _deleteUser

    fun getUsers(query: String? = null) {
        viewModelScope.launch {
            authRepository.getUsers(query).collect {
                _usersData.value = it
            }
        }
    }

    fun deleteUser(userId: Int) {
        viewModelScope.launch {
            authRepository.deleteUser(userId).collect {
                _deleteUser.value = it
            }
        }
    }
}