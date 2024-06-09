package com.anismhub.ticketsystem.presentation.screen.settings

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
class SettingsViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _profileData: MutableStateFlow<Resource<Profile>> =
        MutableStateFlow(Resource.None)
    val profileData: StateFlow<Resource<Profile>> = _profileData

    init {
        getProfile()
    }

    private fun getProfile() {
        viewModelScope.launch {
            authRepository.getProfile().collect {
                _profileData.value = it
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            authRepository.clearLoginData()
        }
    }
}