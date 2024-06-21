package com.anismhub.ticketsystem.presentation.screen.settings.changepassword

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
import com.anismhub.ticketsystem.R
import com.anismhub.ticketsystem.presentation.components.InputTextWithLabel

@Composable
fun ChangePasswordScreen(modifier: Modifier = Modifier) {
    var currentPasswordValue by remember { mutableStateOf("") }
    var currentPasswordVisibility by remember { mutableStateOf(false) }

    var passwordValue by remember { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false) }

    var passwordConfirmationValue by remember { mutableStateOf("") }
    var passwordConfirmationVisibility by remember { mutableStateOf(false) }


    Column(modifier = modifier.padding(16.dp)) {
        InputTextWithLabel(
            title = "Password saat ini",
            value = currentPasswordValue,
            onValueChange = { currentPasswordValue = it },
            keyboardOption = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = if (currentPasswordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val icon =
                    if (currentPasswordVisibility) painterResource(R.drawable.visibility_off_24px) else
                        painterResource(R.drawable.visibility_24px)
                val desc =
                    if (currentPasswordVisibility) "Hide password" else "Show password"

                IconButton(onClick = { currentPasswordVisibility = !currentPasswordVisibility }) {
                    Icon(painter = icon, contentDescription = desc)
                }
            },
        )
        Spacer(modifier = Modifier.height(16.dp))

        InputTextWithLabel(
            title = "Password Baru",
            value = passwordValue,
            onValueChange = { passwordValue = it },
            keyboardOption = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val icon =
                    if (passwordVisibility) painterResource(R.drawable.visibility_off_24px) else
                        painterResource(R.drawable.visibility_24px)
                val desc =
                    if (passwordVisibility) "Hide password" else "Show password"

                IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                    Icon(painter = icon, contentDescription = desc)
                }
            },
        )

        Spacer(modifier = Modifier.height(16.dp))

        InputTextWithLabel(
            title = "Konfirmasi Password Baru",
            value = passwordConfirmationValue,
            onValueChange = { passwordConfirmationValue = it },
            keyboardOption = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = if (passwordConfirmationVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val icon =
                    if (passwordConfirmationVisibility) painterResource(R.drawable.visibility_off_24px) else
                        painterResource(R.drawable.visibility_24px)
                val desc =
                    if (passwordConfirmationVisibility) "Hide password" else "Show password"

                IconButton(onClick = {
                    passwordConfirmationVisibility = !passwordConfirmationVisibility
                }) {
                    Icon(painter = icon, contentDescription = desc)
                }
            },
        )

        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = { },
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