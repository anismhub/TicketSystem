package com.anismhub.ticketsystem.presentation.screen.notification

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anismhub.ticketsystem.domain.model.Notification
import com.anismhub.ticketsystem.domain.repository.AuthRepository
import com.anismhub.ticketsystem.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotificationViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _notification: MutableStateFlow<Resource<Notification>> = MutableStateFlow(Resource.None)
    val notification: StateFlow<Resource<Notification>> = _notification

    init {
        getNotification()
    }

    private fun getNotification() {
        viewModelScope.launch {
            authRepository.getNotification().collect {
                _notification.value = it
            }
        }
    }
}