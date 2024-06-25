package com.anismhub.ticketsystem.presentation.screen.settings.changepassword

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anismhub.ticketsystem.domain.model.ProfileData
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
class ChangePasswordViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {
    private val _changePassword = MutableStateFlow<Event<Resource<Response>>>(Event(Resource.None))
    val changePassword: StateFlow<Event<Resource<Response>>> = _changePassword

    private val _localProfileData = mutableStateOf<ProfileData?>(null)
    val localProfileData: State<ProfileData?> = _localProfileData

    init {
        getLocalProfile()
    }

    fun changePassword(
        currentPassword: String,
        newPassword: String,
    ) {
        viewModelScope.launch {
            authRepository.postChangePassword(currentPassword, newPassword).collect {
                _changePassword.value = Event(it)
            }
        }
    }

    private fun getLocalProfile() {
        viewModelScope.launch {
            authRepository.getProfileData().collect {
                _localProfileData.value = it
            }
        }
    }
}