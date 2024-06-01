package com.anismhub.ticketsystem.presentation.screen.accounts.update

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.anismhub.ticketsystem.presentation.common.InputTextState
import com.anismhub.ticketsystem.presentation.common.departmentOptions
import com.anismhub.ticketsystem.presentation.common.roleOptions
import com.anismhub.ticketsystem.presentation.components.DropdownMenuWithLabel
import com.anismhub.ticketsystem.presentation.components.InputTextWithLabel
import com.anismhub.ticketsystem.presentation.theme.MyTypography

@Composable
fun AccountsUpdateScreen(modifier: Modifier = Modifier) {

}

@Composable
fun AccountsUpdateContent(modifier: Modifier = Modifier) {
    val username by remember { mutableStateOf(InputTextState()) }
    val fullname by remember { mutableStateOf(InputTextState()) }
    val phoneNumber by remember { mutableStateOf(InputTextState()) }

    val expandedDepartment by remember { mutableStateOf(false) }
    val expandedRole by remember { mutableStateOf(false) }


    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 16.dp)
            .padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Perbarui Pengguna",
            style = MyTypography.headlineMedium.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(8.dp))
        // Username
        InputTextWithLabel(
            title = "Username",
            initialTextState = username,
            enabled = false
        )
        // Fullname
        InputTextWithLabel(
            title = "Nama Lengkap",
            initialTextState = fullname,
        )
        // Role
        DropdownMenuWithLabel(
            title = "Role",
            expandedValue = expandedRole,
            options = roleOptions
        )
        // Departemen
        DropdownMenuWithLabel(
            title = "Departemen",
            expandedValue = expandedDepartment,
            options = departmentOptions
        )

        // Phone Number
        InputTextWithLabel(
            title = "Nomor Telepon",
            initialTextState = phoneNumber,
        )
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