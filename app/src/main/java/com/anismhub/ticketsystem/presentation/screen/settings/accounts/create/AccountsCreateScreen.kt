package com.anismhub.ticketsystem.presentation.screen.settings.accounts.create

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.anismhub.ticketsystem.presentation.common.departmentOptions
import com.anismhub.ticketsystem.presentation.common.roleOptions
import com.anismhub.ticketsystem.presentation.components.DropdownMenuWithLabel
import com.anismhub.ticketsystem.presentation.components.InputTextWithLabel

@Composable
fun AccountsCreateScreen() {

}

@Composable
fun AccountsCreateContent(modifier: Modifier = Modifier) {
    var username by remember { mutableStateOf("") }
    var fullname by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val confirmPassword by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var selectedRole by remember { mutableStateOf("") }
    var selectedDepartment by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 16.dp)
            .padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Username
        InputTextWithLabel(title = "Username", value = username, onValueChange = { username = it})
        // Fullname
        InputTextWithLabel(title = "Nama Lengkap", value = fullname, onValueChange = { fullname = it})
        // Role
        DropdownMenuWithLabel(title = "Role", value = selectedRole,
            onValueChange = { selectedRole = it },
            options = roleOptions)
        // Departemen
        DropdownMenuWithLabel(
            title = "Departemen",
            value = selectedDepartment,
            onValueChange = { selectedDepartment = it },
            options = departmentOptions
        )
        // Phone Number
        InputTextWithLabel(title = "Nomor Telepon", value = phoneNumber, onValueChange = { phoneNumber = it})
        // Password
        InputTextWithLabel(
            title = "Password",
            value = password,
            onValueChange = { password = it },
            keyboardOption = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
//        // Confirmed Password
//        InputTextWithLabel(
//            title = "Konfirmasi Password",
//            initialTextState = confirmPassword,
//            keyboardOption = KeyboardOptions(keyboardType = KeyboardType.Password)
//        )
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = { /*TODO*/ },
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