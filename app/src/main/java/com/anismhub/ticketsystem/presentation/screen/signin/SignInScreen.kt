package com.anismhub.ticketsystem.presentation.screen.signin

import androidx.compose.foundation.Image
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.anismhub.ticketsystem.R
import com.anismhub.ticketsystem.presentation.common.InputTextState
import com.anismhub.ticketsystem.presentation.components.InputText
import com.anismhub.ticketsystem.presentation.theme.MyTypography
import com.anismhub.ticketsystem.presentation.theme.TicketSystemTheme

@Composable
fun SignInScreen(
    navigateToHome: () -> Unit,
    modifier: Modifier = Modifier
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

    SignInContent(
        navigateToHome = navigateToHome,
        username = username,
        password = password,
        passwordVisibility = passwordVisibility,
        onPasswordVisibilityChange = { passwordVisibility = it },
        onUsernameChange = { username = it },
        onPasswordChange = { password = it },
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
    navigateToHome: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
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
            keyboardOption = KeyboardOptions(keyboardType = KeyboardType.Text),
            trailingIcon = {
                if (username.value.isNotEmpty()) {
                    IconButton(onClick = { onUsernameChange(username.copy(value = "")) }) {
                        Icon(imageVector = Icons.Outlined.Close, contentDescription = "")
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
                        isError = newValue.isEmpty() || newValue.length < 6
                    )
                )
            },
            label = "Password",
            isError = password.isError,
            keyboardOption = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val icon =
                    if (passwordVisibility) Icons.Outlined.Visibility else Icons.Outlined.VisibilityOff
                val desc =
                    if (passwordVisibility) "Hide password" else "Show password"

                IconButton(onClick = { onPasswordVisibilityChange(!passwordVisibility) }) {
                    Icon(imageVector = icon, contentDescription = desc)
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
            onClick = { navigateToHome() },
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

@Preview(showSystemUi = true)
@Composable
private fun SignInPreview() {
    TicketSystemTheme {
        SignInContent(
            navigateToHome = {},
            username = InputTextState(),
            password = InputTextState(),
            onUsernameChange = {},
            onPasswordChange = {},
            passwordVisibility = false,
            onPasswordVisibilityChange = {}
        )
    }
}