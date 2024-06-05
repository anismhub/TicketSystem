package com.anismhub.ticketsystem.presentation.screen.main

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anismhub.ticketsystem.domain.repository.AuthRepository
import com.anismhub.ticketsystem.navigation.graph.Graph
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _startDestination: MutableState<String> = mutableStateOf(Graph.NONE)
    val startDestination: State<String> = _startDestination

    init {
        viewModelScope.launch {
            authRepository.getLoginState().collect { isLogin ->
                _startDestination.value = if (isLogin) Graph.MAIN else Graph.AUTHENTICATION
            }
        }
    }
}