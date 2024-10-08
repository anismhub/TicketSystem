package com.anismhub.ticketsystem.presentation.screen.signin

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.anismhub.ticketsystem.R
import com.anismhub.ticketsystem.presentation.common.InputTextState
import com.anismhub.ticketsystem.presentation.components.InputText
import com.anismhub.ticketsystem.presentation.theme.MyTypography
import com.anismhub.ticketsystem.utils.Resource
import com.anismhub.ticketsystem.utils.isInvalid

@Composable
fun SignInScreen(
    navigateToHome: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SignInViewModel = hiltViewModel()
) {

    var passwordVisibility by remember {
        mutableStateOf(false)
    }

    var username by remember {
        mutableStateOf(InputTextState())
    }

    var password by remember {
        mutableStateOf(InputTextState())
    }

    var isSigninEnabled by remember { mutableStateOf(true) }
    var isLoading by remember { mutableStateOf(false) }

    val loginResult by viewModel.loginResource.collectAsStateWithLifecycle()
    val context = LocalContext.current


    loginResult.let {
        if (!it.hasBeenHandled) {
            when (val unhandled = it.getContentIfNotHandled()) {
                is Resource.Loading -> {
                    isSigninEnabled = false
                    isLoading = true
                }

                is Resource.Error -> {
                    Toast.makeText(context, unhandled.error, Toast.LENGTH_SHORT).show()
                    isSigninEnabled = true
                    isLoading = false
                }

                is Resource.Success -> {
                    isLoading = false
                    viewModel.saveLoginData(unhandled.data.data)
                    viewModel.updateFCMToken()
                    navigateToHome()
                }

                else -> {}
            }
        }
    }

    SignInContent(
        username = username,
        password = password,
        passwordVisibility = passwordVisibility,
        onPasswordVisibilityChange = { passwordVisibility = it },
        onUsernameChange = { username = it },
        onPasswordChange = { password = it },
        loginAction = {
            viewModel.login(username.value, password.value)
        },
        isSignInEnabled = isSigninEnabled,
        isLoading = isLoading,
        modifier = modifier
    )
}

@Composable
fun SignInContent(
    username: InputTextState,
    password: InputTextState,
    passwordVisibility: Boolean,
    onPasswordVisibilityChange: (Boolean) -> Unit,
    onUsernameChange: (InputTextState) -> Unit,
    onPasswordChange: (InputTextState) -> Unit,
    loginAction: () -> Unit,
    modifier: Modifier = Modifier,
    isSignInEnabled: Boolean = true,
    isLoading: Boolean = false
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (isLoading) {
            CircularProgressIndicator()
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = "Sign In",
                style = MyTypography.displaySmall.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Image(
                painter = painterResource(id = R.drawable.maintenance),
                contentDescription = "",
                modifier = Modifier
                    .padding(top = 12.dp)
            )

            InputText(
                value = username.value,
                onChange = { newValue ->
                    onUsernameChange(
                        username.copy(
                            value = newValue,
                            isError = newValue.isEmpty()
                        )
                    )
                },
                label = "Username",
                isError = username.isError,
                keyboardOption = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                trailingIcon = {
                    if (username.value.isNotEmpty()) {
                        IconButton(onClick = { onUsernameChange(username.copy(value = "")) }) {
                            Icon(
                                painter = painterResource(id = R.drawable.close_24px),
                                contentDescription = ""
                            )
                        }
                    }
                },
                supportingText = {
                    if (username.isError) {
                        Text(text = "Username can't be empty")
                    }
                }
            )

            InputText(
                value = password.value,
                onChange = { newValue ->
                    onPasswordChange(
                        password.copy(
                            value = newValue,
                            isError = newValue.isEmpty()
                        )
                    )
                },
                label = "Password",
                isError = password.isError,
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
                supportingText = {
                    if (password.isError) {
                        Text(text = "Password at least 6 characters")
                    }
                }
            )

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {
                    when {
                        username.isInvalid() -> onUsernameChange(username.copy(isError = true))
                        password.isInvalid() -> onPasswordChange(password.copy(isError = true))
                        else -> {
                            loginAction()
                        }
                    }
                },
                enabled = isSignInEnabled,
                shape = RoundedCornerShape(16.dp),
                contentPadding = PaddingValues(14.dp),
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Sign In",
                    style = MyTypography.bodyLarge.copy(fontWeight = FontWeight.Medium)
                )
            }
        }
    }
}