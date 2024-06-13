package com.anismhub.ticketsystem.presentation.screen.settings.accounts.update

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.anismhub.ticketsystem.domain.model.DepartmentsData
import com.anismhub.ticketsystem.presentation.common.roleOptions
import com.anismhub.ticketsystem.presentation.components.DropdownMenuWithLabel
import com.anismhub.ticketsystem.presentation.components.InputTextWithLabel
import com.anismhub.ticketsystem.utils.Resource

@Composable
fun AccountsUpdateScreen(
    userId: Int,
    onNavUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: AccountsUpdateViewModel = hiltViewModel()
) {
    LaunchedEffect(userId) {
        viewModel.getUserById(userId)
    }

    val department by viewModel.departments.collectAsStateWithLifecycle()
    val detailUser by viewModel.detailUser.collectAsStateWithLifecycle()
    val editUser by viewModel.editUser.collectAsStateWithLifecycle()

    var username by remember { mutableStateOf("") }
    var fullname by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var selectedRole by remember { mutableStateOf("") }
    var selectedRoleIndex by remember { mutableIntStateOf(0) }
    var listDepartments by remember { mutableStateOf(emptyList<DepartmentsData>()) }
    var selectedDepartment by remember { mutableStateOf("") }
    var selectedDepartmentIndex by remember { mutableIntStateOf(0) }

    when(val result = department) {
        is Resource.Success -> {
            listDepartments = result.data.data
        }
        is Resource.Error -> {
            Log.d("Update Screen Error", result.error)
        }
        else -> {}
    }

    editUser.let {
        if (!it.hasBeenHandled) {
            when (val result = it.getContentIfNotHandled()) {
                is Resource.Loading -> {
                    Log.d("Update Screen Loading", "Loading...")
                }

                is Resource.Success -> {
                    onNavUp()
                }

                is Resource.Error -> {
                    Log.d("Update Screen Error", result.error)
                }

                else -> {}
            }
        }
    }

    LaunchedEffect(detailUser) {
        when (val resultData = detailUser) {
            is Resource.Loading -> {
                Log.d("Update Screen Loading", "Loading...")
            }

            is Resource.Success -> {
                username = resultData.data.data.userName
                fullname = resultData.data.data.userFullName
                phoneNumber = resultData.data.data.userPhone
                selectedRole = resultData.data.data.userRole
                selectedDepartmentIndex = listDepartments.indexOfFirst {
                   it.departmentName == resultData.data.data.departmentName
                }
                selectedDepartment = listDepartments[selectedDepartmentIndex].departmentName
            }

            is Resource.Error -> {
                Log.d("Update Screen Error", resultData.error)
            }

            else -> {}
        }
    }

    AccountsUpdateContent(
        username = username,
        onUsernameChange = { username = it },
        fullname = fullname,
        onFullnameChange = { fullname = it },
        role = selectedRole,
        onRoleChange = { value, index ->
            selectedRole = value
            selectedRoleIndex = index
        },
        listDepartments = listDepartments,
        department = selectedDepartment,
        onDepartmentChange = { value, index ->
            selectedDepartment = value
            selectedDepartmentIndex = index
        },
        phoneNumber = phoneNumber,
        onPhoneChange = { phoneNumber = it },
        updateUser = {
            viewModel.editUser(
                userId = userId,
                username = username,
                fullname = fullname,
                role = selectedRole,
                department = listDepartments[selectedDepartmentIndex].departmentId,
                phoneNumber = phoneNumber
            )
        },
        modifier = modifier
    )
}

@Composable
fun AccountsUpdateContent(
    username: String,
    onUsernameChange: (String) -> Unit,
    fullname: String,
    onFullnameChange: (String) -> Unit,
    role: String,
    onRoleChange: (String, Int) -> Unit,
    department: String,
    onDepartmentChange: (String, Int) -> Unit,
    listDepartments: List<DepartmentsData>,
    phoneNumber: String,
    onPhoneChange: (String) -> Unit,
    updateUser: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 16.dp)
            .padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Username
        InputTextWithLabel(
            title = "Username",
            value = username,
            onValueChange = onUsernameChange,
            enabled = false
        )
        // Fullname
        InputTextWithLabel(
            title = "Nama Lengkap",
            value = fullname,
            onValueChange = onFullnameChange
        )
        // Role
        DropdownMenuWithLabel(
            title = "Role", value = role,
            onValueChange = onRoleChange,
            options = roleOptions
        )
        // Departemen
        DropdownMenuWithLabel(
            title = "Departemen",
            value = department,
            onValueChange = onDepartmentChange,
            options = listDepartments.map { it.departmentName }
        )
        // Phone Number
        InputTextWithLabel(
            title = "Nomor Telepon",
            value = phoneNumber,
            onValueChange = onPhoneChange
        )
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = {
                updateUser()
            },
            shape = RoundedCornerShape(20),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth(0.6f)
                .padding(bottom = 8.dp)
        ) {
            Text(text = "Perbarui")
        }
    }
}