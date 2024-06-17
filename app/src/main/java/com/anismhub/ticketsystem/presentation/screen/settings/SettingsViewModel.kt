package com.anismhub.ticketsystem.presentation.screen.settings

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anismhub.ticketsystem.domain.model.Profile
import com.anismhub.ticketsystem.domain.model.ProfileData
import com.anismhub.ticketsystem.domain.repository.AuthRepository
import com.anismhub.ticketsystem.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : ViewModel() {

    private val _profileData: MutableStateFlow<Resource<Profile>> =
        MutableStateFlow(Resource.None)
    val profileData: StateFlow<Resource<Profile>> = _profileData

    private val _localProfileData: MutableState<ProfileData> =
        mutableStateOf(
            ProfileData(0, "", "", "", 0, "", "")
        )
    val localProfileData: State<ProfileData> = _localProfileData

    init {
        getProfile()
        getLocalProfile()
    }

    private fun getLocalProfile() {
        viewModelScope.launch {
            authRepository.getProfileData().collect {
                _localProfileData.value = it
            }
        }
    }

    private fun getProfile() {
        viewModelScope.launch {
            authRepository.getProfile().collect {
                _profileData.value = it
            }
        }
    }

    fun saveLocalProfile(profileData: ProfileData) {
        viewModelScope.launch {
            authRepository.saveProfileData(profileData)
        }
        getLocalProfile()
    }

    fun logout() {
        viewModelScope.launch {
            authRepository.clearData()
            authRepository.deleteFCMToken()
        }
    }
}