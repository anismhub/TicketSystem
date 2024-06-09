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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.anismhub.ticketsystem.data.DataDummy
import com.anismhub.ticketsystem.presentation.common.departmentOptions
import com.anismhub.ticketsystem.presentation.common.roleOptions
import com.anismhub.ticketsystem.presentation.components.DropdownMenuWithLabel
import com.anismhub.ticketsystem.presentation.components.InputTextWithLabel

@Composable
fun AccountsUpdateScreen() {

}

@Composable
fun AccountsUpdateContent(modifier: Modifier = Modifier) {
    val dummyPengguna = DataDummy.pengguna[0]

    var username by remember { mutableStateOf("jdoe") }
    var fullname by remember { mutableStateOf(dummyPengguna.nama) }
    var phoneNumber by remember { mutableStateOf("") }

    var selectedDepartment by remember { mutableStateOf("") }
    var selectedDepartmentIndex by remember { mutableIntStateOf(0) }
    var selectedRole by remember { mutableStateOf(dummyPengguna.role) }
    var selectedRoleIndex by remember { mutableIntStateOf(0) }


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
            value = username,
            onValueChange = { username = it },
            enabled = false
        )
        // Fullname
        InputTextWithLabel(
            title = "Nama Lengkap",
            value = fullname,
            onValueChange = { fullname = it })
        // Role
        DropdownMenuWithLabel(
            title = "Role", value = selectedRole,
            onValueChange = { value, index ->
                selectedRole = value
                selectedRoleIndex = index
            },
            options = roleOptions
        )
        // Departemen
        DropdownMenuWithLabel(
            title = "Departemen",
            value = selectedDepartment,
            onValueChange = { value, index ->
                selectedDepartment = value
                selectedDepartmentIndex = index
            },
            options = departmentOptions
        )
        // Phone Number
        InputTextWithLabel(
            title = "Nomor Telepon",
            value = phoneNumber,
            onValueChange = { phoneNumber = it })
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