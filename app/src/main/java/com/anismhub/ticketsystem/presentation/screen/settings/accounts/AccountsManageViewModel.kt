package com.anismhub.ticketsystem.presentation.screen.settings.accounts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anismhub.ticketsystem.domain.model.Profile
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

    private val _usersData: MutableStateFlow<Resource<Profile>> = MutableStateFlow(Resource.None)
    val usersData: StateFlow<Resource<Profile>> = _usersData

    private fun getUsers() {
        viewModelScope.launch {
            authRepository.getProfile().collect {
                _usersData.value = it
            }
        }
    }
}