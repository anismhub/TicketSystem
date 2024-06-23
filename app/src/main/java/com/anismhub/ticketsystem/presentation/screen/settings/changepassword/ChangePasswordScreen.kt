package com.anismhub.ticketsystem.presentation.screen.settings.changepassword

import android.util.Log
import android.widget.Toast
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

    changePassword.let {
        if (!it.hasBeenHandled) {
            when (val result = it.getContentIfNotHandled()) {
                is Resource.Loading -> {
                    Log.d("Change Password Loading", "Loading changing passwod...")
                }

                is Resource.Success -> {
                    Toast.makeText(context, "Password Berhasil Diubah", Toast.LENGTH_SHORT).show()
                }

                is Resource.Error -> {
                    Log.d("Update Screen Error", result.error)
                }

                else -> {}
            }
        }
    }

    ChangePasswordContent(
        changePassword = { viewModel.changePassword(localProfile!!.userId, it) },
        password = password,
        onPasswordChange = { password = it },
        passwordVisibility = passwordVisibility,
        onPasswordVisibilityChange = { passwordVisibility = it },
        passwordConfirmation = passwordConfirmation,
        onPasswordConfirmationChange = { passwordConfirmation = it },
        passwordConfirmationVisibility = passwordConfirmationVisibility,
        onPasswordConfirmationVisibilityChange = { passwordConfirmationVisibility = it },
        modifier = modifier
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
    modifier: Modifier = Modifier
) {
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
        Button(
            onClick = {
                when {
                    password.isInvalid() -> onPasswordChange(password.copy(isError = true))
                    passwordConfirmation.isInvalid() -> onPasswordConfirmationChange(
                        passwordConfirmation.copy(isError = true)
                    )

                    password.value != passwordConfirmation.value -> {
                        onPasswordConfirmationChange(passwordConfirmation.copy(isError = true))
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