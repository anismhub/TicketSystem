package com.anismhub.ticketsystem.presentation.screen.settings.changepassword

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.anismhub.ticketsystem.R
import com.anismhub.ticketsystem.presentation.common.InputTextState
import com.anismhub.ticketsystem.presentation.components.InputTextWithLabel
import com.anismhub.ticketsystem.utils.Resource
import com.anismhub.ticketsystem.utils.isInvalid

@Composable
fun ChangePasswordScreen(
    modifier: Modifier = Modifier,
    viewModel: ChangePasswordViewModel = hiltViewModel()
) {
    val changePassword by viewModel.changePassword.collectAsStateWithLifecycle()

    var currentPassword by remember { mutableStateOf(InputTextState()) }
    var currentPasswordVisibility by remember { mutableStateOf(false) }

    var password by remember { mutableStateOf(InputTextState()) }
    var passwordVisibility by remember { mutableStateOf(false) }

    var passwordConfirmation by remember { mutableStateOf(InputTextState()) }
    var passwordConfirmationVisibility by remember { mutableStateOf(false) }

    var isSuccesss by remember { mutableStateOf(false) }
    var isError by remember { mutableStateOf(false) }
    var errorText by remember { mutableStateOf("") }

    changePassword.let {
        if (!it.hasBeenHandled) {
            when (val result = it.getContentIfNotHandled()) {
                is Resource.Loading -> {
                    isSuccesss = false
                    isError = false
                }

                is Resource.Success -> {
                    currentPassword = InputTextState()
                    password = InputTextState()
                    passwordConfirmation = InputTextState()
                    isSuccesss = true
                    isError = false
                }

                is Resource.Error -> {
                    errorText = result.error
                    Log.d("Update Screen Error", result.error)
                    isSuccesss = false
                    isError = true
                }

                else -> {}
            }
        }
    }

    ChangePasswordContent(
        changePassword = { oldPassword, newPassword ->
            viewModel.changePassword(
                oldPassword,
                newPassword
            )
        },
        currentPassword = currentPassword,
        onCurrentPasswordChange = { currentPassword = it },
        currentPasswordVisibility = currentPasswordVisibility,
        onCurrentPasswordVisibilityChange = { currentPasswordVisibility = it },
        password = password,
        onPasswordChange = { password = it },
        passwordVisibility = passwordVisibility,
        onPasswordVisibilityChange = { passwordVisibility = it },
        passwordConfirmation = passwordConfirmation,
        onPasswordConfirmationChange = { passwordConfirmation = it },
        passwordConfirmationVisibility = passwordConfirmationVisibility,
        onPasswordConfirmationVisibilityChange = { passwordConfirmationVisibility = it },
        modifier = modifier,
        isSuccess = isSuccesss,
        isError = isError,
        errorText = errorText
    )
}

@Composable
fun ChangePasswordContent(
    changePassword: (String, String) -> Unit,
    currentPassword: InputTextState,
    onCurrentPasswordChange: (InputTextState) -> Unit,
    currentPasswordVisibility: Boolean,
    onCurrentPasswordVisibilityChange: (Boolean) -> Unit,
    password: InputTextState,
    onPasswordChange: (InputTextState) -> Unit,
    passwordVisibility: Boolean,
    onPasswordVisibilityChange: (Boolean) -> Unit,
    passwordConfirmation: InputTextState,
    onPasswordConfirmationChange: (InputTextState) -> Unit,
    passwordConfirmationVisibility: Boolean,
    onPasswordConfirmationVisibilityChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    isSuccess: Boolean = false,
    isError: Boolean = false,
    errorText: String = "Gagal Ganti Password"
) {
    var passwordErrorText by remember { mutableStateOf("Password harus diisi") }
    var passwordConfirmationErrorText by remember { mutableStateOf("Konfirmasi Password harus diisi") }

    Column(modifier = modifier.padding(16.dp)) {
        InputTextWithLabel(
            title = "Password Sekarang",
            textState = currentPassword,
            onValueChange = onCurrentPasswordChange,
            keyboardOption = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = if (currentPasswordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val icon =
                    if (currentPasswordVisibility) painterResource(R.drawable.visibility_off_24px) else
                        painterResource(R.drawable.visibility_24px)
                val desc = if (currentPasswordVisibility) "Hide password" else "Show password"

                IconButton(onClick = { onCurrentPasswordVisibilityChange(!currentPasswordVisibility) }) {
                    Icon(painter = icon, contentDescription = desc)
                }
            },
        )
        Spacer(modifier = Modifier.height(16.dp))

        InputTextWithLabel(
            title = "Password Baru",
            errorText = passwordErrorText,
            textState = password,
            onValueChange = onPasswordChange,
            keyboardOption = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val icon =
                    if (passwordVisibility) painterResource(R.drawable.visibility_off_24px) else
                        painterResource(R.drawable.visibility_24px)
                val desc = if (passwordVisibility) "Hide password" else "Show password"
                IconButton(onClick = { onPasswordVisibilityChange(!passwordVisibility) }) {
                    Icon(painter = icon, contentDescription = desc)
                }
            },
        )

        Spacer(modifier = Modifier.height(16.dp))

        InputTextWithLabel(
            title = "Konfirmasi Password Baru",
            errorText = passwordConfirmationErrorText,
            textState = passwordConfirmation,
            onValueChange = onPasswordConfirmationChange,
            keyboardOption = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = if (passwordConfirmationVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val icon =
                    if (passwordConfirmationVisibility) painterResource(R.drawable.visibility_off_24px) else
                        painterResource(R.drawable.visibility_24px)
                val desc = if (passwordConfirmationVisibility) "Hide password" else "Show password"
                IconButton(onClick = { onPasswordConfirmationVisibilityChange(!passwordConfirmationVisibility) }) {
                    Icon(painter = icon, contentDescription = desc)
                }
            },
        )

        Spacer(modifier = Modifier.weight(1f))
        when {
            isSuccess -> {
                Text(
                    text = "Password berhasil diubah",
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }

            isError -> {
                Text(
                    text = errorText,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
        }
        Button(
            onClick = {
                when {
                    currentPassword.isInvalid() -> {
                        onPasswordChange(password.copy(isError = true))
                        passwordErrorText = "Password sekarang harus diisi"
                    }

                    password.isInvalid() -> {
                        onPasswordChange(password.copy(isError = true))
                        passwordErrorText = "Password harus diisi"
                    }

                    password.value.length < 6 -> {
                        onPasswordChange(password.copy(isError = true))
                        passwordErrorText = "Password minimal 6 karakter"
                    }

                    passwordConfirmation.isInvalid() -> {
                        onPasswordConfirmationChange(
                            passwordConfirmation.copy(isError = true)
                        )
                        passwordConfirmationErrorText = "Konfirmasi Password harus diisi"
                    }


                    password.value != passwordConfirmation.value -> {
                        onPasswordConfirmationChange(passwordConfirmation.copy(isError = true))
                        passwordConfirmationErrorText = "Password tidak cocok"
                    }

                    else -> {
                        changePassword(currentPassword.value, passwordConfirmation.value)
                    }
                }
            },
            shape = RoundedCornerShape(20),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth(0.6f)
                .padding(bottom = 8.dp)
        ) {
            Text(text = "Ganti Password")
        }
    }
}