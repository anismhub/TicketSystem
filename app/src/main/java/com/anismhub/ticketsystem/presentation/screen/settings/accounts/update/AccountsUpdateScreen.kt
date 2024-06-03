package com.anismhub.ticketsystem.presentation.screen.settings.accounts.update

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.anismhub.ticketsystem.presentation.common.departmentOptions
import com.anismhub.ticketsystem.presentation.common.roleOptions
import com.anismhub.ticketsystem.presentation.components.DropdownMenuWithLabel
import com.anismhub.ticketsystem.presentation.components.InputTextWithLabel

@Composable
fun AccountsUpdateScreen() {

}

@Composable
fun AccountsUpdateContent(modifier: Modifier = Modifier) {
    val username by remember { mutableStateOf("") }
    val fullname by remember { mutableStateOf("") }
    val phoneNumber by remember { mutableStateOf("") }

    val selectedDepartment by remember { mutableStateOf("") }
    val selectedRole by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 16.dp)
            .padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Username
        InputTextWithLabel(title = "Username", initialTextState = username, enabled = false)
        // Fullname
        InputTextWithLabel(title = "Nama Lengkap", initialTextState = fullname)
        // Role
        DropdownMenuWithLabel(title = "Role", selectedValue = selectedRole, options = roleOptions)
        // Departemen
        DropdownMenuWithLabel(
            title = "Departemen",
            selectedValue = selectedDepartment,
            options = departmentOptions
        )
        // Phone Number
        InputTextWithLabel(title = "Nomor Telepon", initialTextState = phoneNumber)
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = { /*TODO*/ },
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