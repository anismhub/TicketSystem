package com.anismhub.ticketsystem.presentation.screen.settings.accounts.update

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anismhub.ticketsystem.domain.model.Departments
import com.anismhub.ticketsystem.domain.model.Profile
import com.anismhub.ticketsystem.domain.model.Response
import com.anismhub.ticketsystem.domain.repository.AuthRepository
import com.anismhub.ticketsystem.domain.repository.ResourcesRepository
import com.anismhub.ticketsystem.utils.Event
import com.anismhub.ticketsystem.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountsUpdateViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val resourcesRepository: ResourcesRepository
) : ViewModel() {
    private val _detailUser = MutableStateFlow<Resource<Profile>>(Resource.None)
    val detailUser: StateFlow<Resource<Profile>> = _detailUser

    private val _editUser = MutableStateFlow<Event<Resource<Response>>>(Event(Resource.None))
    val editUser: StateFlow<Event<Resource<Response>>> = _editUser

    private val _departments = MutableStateFlow<Resource<Departments>>(Resource.None)
    val departments: StateFlow<Resource<Departments>> = _departments

    init {
        getDepartments()
    }

    private fun getDepartments() {
        viewModelScope.launch {
            resourcesRepository.getDepartments().collect {
                _departments.value = it
            }
        }
    }

    fun getUserById(userId: Int) {
        viewModelScope.launch {
            authRepository.getUserById(userId).collect {
                _detailUser.value = it
            }
        }
    }

    fun editUser(
        userId: Int,
        username: String,
        fullname: String,
        role: String,
        department: Int,
        phoneNumber: String,
    ) {
        viewModelScope.launch {
            authRepository.postEditUser(userId, username, fullname, role, department, phoneNumber)
                .collect {
                    _editUser.value = Event(it)
                }
        }
    }
}