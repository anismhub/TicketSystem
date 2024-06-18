package com.anismhub.ticketsystem.presentation.screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anismhub.ticketsystem.domain.model.ProfileData
import com.anismhub.ticketsystem.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {
    private val _localProfileData = mutableStateOf<ProfileData?>(null)
    val localProfileData: State<ProfileData?> = _localProfileData

    init {
        getLocalProfile()
    }

    private fun getLocalProfile() {
        viewModelScope.launch {
            authRepository.getProfileData().collect {
                _localProfileData.value = it
            }
        }
    }
}