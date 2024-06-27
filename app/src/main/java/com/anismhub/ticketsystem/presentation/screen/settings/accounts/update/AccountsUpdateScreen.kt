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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.anismhub.ticketsystem.R
import com.anismhub.ticketsystem.domain.model.DepartmentsData
import com.anismhub.ticketsystem.presentation.common.DropdownMenuState
import com.anismhub.ticketsystem.presentation.common.InputTextState
import com.anismhub.ticketsystem.presentation.common.roleOptions
import com.anismhub.ticketsystem.presentation.components.DropdownMenuWithLabel
import com.anismhub.ticketsystem.presentation.components.InputTextWithLabel
import com.anismhub.ticketsystem.utils.Resource
import com.anismhub.ticketsystem.utils.isInvalid

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

    var username by remember { mutableStateOf(InputTextState()) }
    var fullname by remember { mutableStateOf(InputTextState()) }
    var phoneNumber by remember { mutableStateOf(InputTextState()) }
    var selectedRole by remember { mutableStateOf(DropdownMenuState("Pilih Role", -1)) }
    var selectedRoleIndex by remember { mutableIntStateOf(0) }
    var listDepartments by remember { mutableStateOf(emptyList<DepartmentsData>()) }
    var selectedDepartment by remember { mutableStateOf(DropdownMenuState("Pilih Department", 0)) }
    var selectedDepartmentIndex by remember { mutableIntStateOf(0) }

    when (val result = department) {
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
                username = username.copy(value = resultData.data.data.userName)
                fullname = fullname.copy(value = resultData.data.data.userFullName)
                phoneNumber = phoneNumber.copy(value = resultData.data.data.userPhone)
                selectedRole =
                    selectedRole.copy(text = resultData.data.data.userRole, indexValue = roleOptions.indexOfFirst {
                        it == resultData.data.data.userRole
                    })
                selectedRoleIndex = roleOptions.indexOfFirst {
                    it == resultData.data.data.userRole
                }
                selectedDepartmentIndex = listDepartments.indexOfFirst {
                    it.departmentName == resultData.data.data.departmentName
                }
                selectedDepartment = selectedDepartment.copy(
                    text = resultData.data.data.departmentName,
                    indexValue = selectedDepartmentIndex + 1
                )
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
            selectedRole = selectedRole.copy(
                text = value,
                indexValue = index,
                isError = false
            )
            selectedRoleIndex = index
        },
        onRoleError = { selectedRole = it },
        listDepartments = listDepartments,
        department = selectedDepartment,
        onDepartmentChange = { value, index ->
            selectedDepartment = selectedDepartment.copy(
                text = value,
                indexValue = index + 1,
                isError = false
            )
            selectedDepartmentIndex = index
        },
        onDepartmentError = { selectedDepartment = it },
        phoneNumber = phoneNumber,
        onPhoneChange = { phoneNumber = it },
        updateUser = {
            viewModel.editUser(
                userId = userId,
                username = username.value,
                fullname = fullname.value,
                role = selectedRole.text,
                department = listDepartments[selectedDepartmentIndex].departmentId,
                phoneNumber = phoneNumber.value
            )
        },
        modifier = modifier
    )
}

@Composable
fun AccountsUpdateContent(
    username: InputTextState,
    onUsernameChange: (InputTextState) -> Unit,
    fullname: InputTextState,
    onFullnameChange: (InputTextState) -> Unit,
    role: DropdownMenuState,
    onRoleChange: (String, Int) -> Unit,
    onRoleError: (DropdownMenuState) -> Unit,
    department: DropdownMenuState,
    onDepartmentChange: (String, Int) -> Unit,
    onDepartmentError: (DropdownMenuState) -> Unit,
    listDepartments: List<DepartmentsData>,
    phoneNumber: InputTextState,
    onPhoneChange: (InputTextState) -> Unit,
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
            textState = username,
            onValueChange = onUsernameChange,
            enabled = false
        )
        // Fullname
        InputTextWithLabel(
            title = "Nama Lengkap",
            textState = fullname,
            onValueChange = onFullnameChange,
            trailingIcon = {
                IconButton(onClick = {
                    onFullnameChange(
                        fullname.copy(
                            value = "",
                            isError = true
                        )
                    )
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.close_24px),
                        contentDescription = ""
                    )
                }
            }
        )
        // Role
        DropdownMenuWithLabel(
            title = "Role",
            value = role.text,
            onValueChange = onRoleChange,
            options = roleOptions
        )
        // Departemen
        DropdownMenuWithLabel(
            title = "Departemen",
            value = department.text,
            onValueChange = onDepartmentChange,
            options = listDepartments.map { it.departmentName }
        )
        // Phone Number
        InputTextWithLabel(
            title = "Nomor Telepon",
            textState = phoneNumber,
            onValueChange = onPhoneChange
        ) {
            IconButton(onClick = { onPhoneChange(phoneNumber.copy(value = "", isError = true)) }) {
                Icon(
                    painter = painterResource(id = R.drawable.close_24px),
                    contentDescription = ""
                )
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = {
                when {
                    username.isInvalid() -> onUsernameChange(username.copy(isError = true))
                    fullname.isInvalid() -> onFullnameChange(fullname.copy(isError = true))
                    role.isInvalid(0) -> onRoleError(role.copy(isError = true))
                    department.isInvalid(1) -> onDepartmentError(department.copy(isError = true))
                    phoneNumber.isInvalid() -> onPhoneChange(phoneNumber.copy(isError = true))
                    else -> updateUser()
                }

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