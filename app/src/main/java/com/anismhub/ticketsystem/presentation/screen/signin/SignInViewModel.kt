package com.anismhub.ticketsystem.presentation.screen.signin

import androidx.lifecycle.ViewModel
import com.anismhub.ticketsystem.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {
    
}