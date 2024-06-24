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
import androidx.compose.ui.platform.LocalContext
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
    val context = LocalContext.current
    val changePassword by viewModel.changePassword.collectAsStateWithLifecycle()
    val localProfile by viewModel.localProfileData

    var currentPassword by remember { mutableStateOf(InputTextState()) }
    var currentPasswordVisibility by remember { mutableStateOf(false) }

    var password by remember { mutableStateOf(InputTextState()) }
    var passwordVisibility by remember { mutableStateOf(false) }

    var passwordConfirmation by remember { mutableStateOf(InputTextState()) }
    var passwordConfirmationVisibility by remember { mutableStateOf(false) }

    var isSuccesss by remember { mutableStateOf(false) }

    changePassword.let {
        if (!it.hasBeenHandled) {
            when (val result = it.getContentIfNotHandled()) {
                is Resource.Loading -> {
                    Log.d("Change Password Loading", "Loading changing passwod...")
                    isSuccesss = false
                }

                is Resource.Success -> {
//                    Toast.makeText(context, "Password Berhasil Diubah", Toast.LENGTH_SHORT).show()
                    password = InputTextState()
                    passwordConfirmation = InputTextState()
                    isSuccesss = true
                }

                is Resource.Error -> {
                    Log.d("Update Screen Error", result.error)
                    isSuccesss = false
                }

                else -> {}
            }
        }
    }

    ChangePasswordContent(
        changePassword = { viewModel.changePassword(it) },
        password = password,
        onPasswordChange = { password = it },
        passwordVisibility = passwordVisibility,
        onPasswordVisibilityChange = { passwordVisibility = it },
        passwordConfirmation = passwordConfirmation,
        onPasswordConfirmationChange = { passwordConfirmation = it },
        passwordConfirmationVisibility = passwordConfirmationVisibility,
        onPasswordConfirmationVisibilityChange = { passwordConfirmationVisibility = it },
        modifier = modifier,
        isSuccess = isSuccesss
    )
}

@Composable
fun ChangePasswordContent(
    changePassword: (String) -> Unit,
    password: InputTextState,
    onPasswordChange: (InputTextState) -> Unit,
    passwordVisibility: Boolean,
    onPasswordVisibilityChange: (Boolean) -> Unit,
    passwordConfirmation: InputTextState,
    onPasswordConfirmationChange: (InputTextState) -> Unit,
    passwordConfirmationVisibility: Boolean,
    onPasswordConfirmationVisibilityChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    isSuccess: Boolean = false
) {
    var passwordErrorText by remember { mutableStateOf("Password harus diisi") }
    var passwordConfirmationErrorText by remember { mutableStateOf("Konfirmasi Password harus diisi") }
    Column(modifier = modifier.padding(16.dp)) {
//        InputTextWithLabel(
//            title = "Password saat ini",
//            textState = currentPassword,
//            onValueChange = { currentPassword = it },
//            keyboardOption = KeyboardOptions(keyboardType = KeyboardType.Password),
//            visualTransformation = if (currentPasswordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
//            trailingIcon = {
//                val icon =
//                    if (currentPasswordVisibility) painterResource(R.drawable.visibility_off_24px) else
//                        painterResource(R.drawable.visibility_24px)
//                val desc = if (currentPasswordVisibility) "Hide password" else "Show password"
//
//                IconButton(onClick = { currentPasswordVisibility = !currentPasswordVisibility }) {
//                    Icon(painter = icon, contentDescription = desc)
//                }
//            },
//        )
//        Spacer(modifier = Modifier.height(16.dp))

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
        if (isSuccess) {
            Text(text = "Password berhasil diubah", modifier = Modifier.align(Alignment.CenterHorizontally))
        }
        Button(
            onClick = {
                when {
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
                        changePassword(passwordConfirmation.value)
                    }
                }
            },
            shape = RoundedCornerShape(20),
//            enabled = password.value.isNotEmpty() && passwordConfirmation.value.isNotEmpty(),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth(0.6f)
                .padding(bottom = 8.dp)
        ) {
            Text(text = "Ganti Password")
        }
    }
}