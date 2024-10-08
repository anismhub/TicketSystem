package com.anismhub.ticketsystem.presentation.screen.settings.accounts.create

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.anismhub.ticketsystem.R
import com.anismhub.ticketsystem.presentation.common.DropdownMenuState
import com.anismhub.ticketsystem.presentation.common.InputTextState
import com.anismhub.ticketsystem.presentation.common.departmentOptions
import com.anismhub.ticketsystem.presentation.common.roleOptions
import com.anismhub.ticketsystem.presentation.components.DropdownMenuWithLabel
import com.anismhub.ticketsystem.presentation.components.InputTextWithLabel
import com.anismhub.ticketsystem.utils.Resource
import com.anismhub.ticketsystem.utils.isInvalid

@Composable
fun AccountsCreateScreen(
    onNavUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: AccountsCreateViewModel = hiltViewModel()
) {
    val addUser by viewModel.addUser.collectAsStateWithLifecycle()

    var username by remember { mutableStateOf(InputTextState()) }
    var fullname by remember { mutableStateOf(InputTextState()) }
    var password by remember { mutableStateOf(InputTextState()) }
    var passwordVisibility by remember { mutableStateOf(false) }
    var phoneNumber by remember { mutableStateOf(InputTextState()) }
    var selectedRole by remember { mutableStateOf(DropdownMenuState("Pilih Role", -1)) }
    var selectedRoleIndex by remember { mutableIntStateOf(0) }
    var selectedDepartment by remember { mutableStateOf(DropdownMenuState("Pilih Department", 0)) }
    var selectedDepartmentIndex by remember { mutableIntStateOf(0) }

    addUser.let {
        if (!it.hasBeenHandled) {
            when (val result = it.getContentIfNotHandled()) {
                is Resource.Loading -> {
                    Toast.makeText(LocalContext.current, "Loading", Toast.LENGTH_SHORT).show()
                }

                is Resource.Success -> {
                    onNavUp()
                }

                is Resource.Error -> {
                    Toast.makeText(LocalContext.current, result.error, Toast.LENGTH_SHORT).show()
                }

                else -> {}
            }
        }
    }

    AccountsCreateContent(
        createUser = {
            viewModel.addUser(
                username = username.value,
                fullname = fullname.value,
                password = password.value,
                role = selectedRole.text,
                department = selectedDepartment.indexValue,
                phoneNumber = phoneNumber.value,
            )
        },
        username = username,
        onUsernameChange = { username = it },
        fullname = fullname,
        onFullnameChange = { fullname = it },
        role = selectedRole,
        onRoleChange = { role, index ->
            selectedRole = selectedRole.copy(
                text = role,
                indexValue = index,
                isError = false
            )
            selectedRoleIndex = index
        },
        onRoleError = { selectedRole = it },
        department = selectedDepartment,
        onDepartmentChange = { department, index ->
            selectedDepartment = selectedDepartment.copy(
                text = department,
                indexValue = index + 1,
                isError = false
            )
            selectedDepartmentIndex = index
        },
        onDepartmentError = { selectedDepartment = it },
        phoneNumber = phoneNumber,
        onPhoneNumberChange = { phoneNumber = it },
        password = password,
        onPasswordChange = { password = it },
        passwordVisibility = passwordVisibility,
        onPasswordVisibilityChange = { passwordVisibility = it },
        modifier = modifier
    )
}

@Composable
fun AccountsCreateContent(
    createUser: () -> Unit,
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
    phoneNumber: InputTextState,
    onPhoneNumberChange: (InputTextState) -> Unit,
    password: InputTextState,
    onPasswordChange: (InputTextState) -> Unit,
    passwordVisibility: Boolean,
    onPasswordVisibilityChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    var passwordErrorText by remember { mutableStateOf("Password tidak boleh kurang dari 6 karakter") }
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
            trailingIcon = {
                IconButton(onClick = {
                    onUsernameChange(
                        username.copy(
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
            title = "Role", value = role.text,
            onValueChange = onRoleChange,
            options = roleOptions,
            isError = role.isError
        )
        // Departemen
        DropdownMenuWithLabel(
            title = "Department",
            value = department.text,
            onValueChange = onDepartmentChange,
            options = departmentOptions,
            isError = department.isError
        )
        // Phone Number
        InputTextWithLabel(
            title = "Nomor Telepon",
            textState = phoneNumber,
            onValueChange = onPhoneNumberChange,
            trailingIcon = {
                IconButton(onClick = {
                    onPhoneNumberChange(
                        phoneNumber.copy(
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
        // Password
        InputTextWithLabel(
            title = "Password",
            errorText = passwordErrorText,
            textState = password,
            onValueChange = {
                onPasswordChange(
                    password.copy(
                        isError = it.value.length < 6,
                        value = it.value
                    )
                )
            },
            keyboardOption = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val icon =
                    if (passwordVisibility) painterResource(R.drawable.visibility_off_24px) else
                        painterResource(R.drawable.visibility_24px)
                val desc =
                    if (passwordVisibility) "Hide password" else "Show password"

                IconButton(onClick = { onPasswordVisibilityChange(!passwordVisibility) }) {
                    Icon(painter = icon, contentDescription = desc)
                }
            },
        )
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = {
                when {
                    username.isInvalid() -> onUsernameChange(username.copy(isError = true))
                    fullname.isInvalid() -> onFullnameChange(fullname.copy(isError = true))
                    role.isInvalid(0) -> onRoleError(role.copy(isError = true))
                    department.isInvalid(1) -> onDepartmentError(department.copy(isError = true))
                    phoneNumber.isInvalid() -> onPhoneNumberChange(phoneNumber.copy(isError = true))
                    password.isInvalid() -> {
                        onPasswordChange(password.copy(isError = true))
                        passwordErrorText = "Password tidak boleh kosong atau kurang dari 6 karakter"
                    }

                    else -> {
                        createUser()
                    }
                }
            },
            shape = RoundedCornerShape(20),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth(0.6f)
                .padding(bottom = 8.dp)
        ) {
            Text(text = "Buat")
        }
    }

}