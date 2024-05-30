package com.anismhub.ticketsystem.presentation.addticket

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.anismhub.ticketsystem.presentation.common.InputTextState
import com.anismhub.ticketsystem.presentation.components.InputText
import com.anismhub.ticketsystem.presentation.theme.MyTypography
import com.anismhub.ticketsystem.presentation.theme.TicketSystemTheme
import com.anismhub.ticketsystem.presentation.theme.fontFamily

@Composable
fun AddTicketScreen(
    modifier: Modifier = Modifier
) {

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTicketContent(
    modifier: Modifier = Modifier
) {

    val areaOptions = listOf(
        "Admin Building",
        "Air Compressor Room",
        "Coal & Ash Handling Control Room",
        "GIS",
        "Jetty",
        "Jakarta Office",
        "Maingate security",
        "Turbine Building",
        "Warehouse Building",
        "Workshop Building"
    )
    var expandedArea by remember { mutableStateOf(false) }
    var selectedArea by remember { mutableStateOf(areaOptions[0]) }

    val priorityOptions = listOf("Rendah", "Sedang", "Tinggi")
    var expandedPriority by remember { mutableStateOf(false) }
    var selectedPriority by remember { mutableStateOf(priorityOptions[0]) }

    val typeTicketOptions = listOf("Install", "Config", "Troubleshoot")
    var expandedTypeTicket by remember { mutableStateOf(false) }
    var selectedTypeTicket by remember { mutableStateOf(typeTicketOptions[0]) }

    var subject by remember { mutableStateOf(InputTextState()) }
    var description by remember { mutableStateOf(InputTextState()) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Buat Tiket Baru",
            style = MyTypography.titleLarge.copy(fontWeight = FontWeight.SemiBold),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Subjek", modifier = Modifier.weight(0.4f))
            InputText(
                value = subject.value,
                onChange = { newValue ->
                    subject = subject.copy(
                        value = newValue,
                        isError = newValue.isEmpty()
                    )
                },
                label = "",
                isError = subject.isError,
                trailingIcon = {
                    if (subject.value.isNotEmpty()) {
                        IconButton(onClick = { subject = subject.copy(value = "") }) {
                            Icon(imageVector = Icons.Outlined.Clear, contentDescription = "")
                        }
                    }
                },
                supportingText = {
                    if (subject.isError) {
                        Text(text = "Subjek harus diisi")
                    }
                },
                modifier = Modifier.weight(0.6f)
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Area", modifier = Modifier.weight(0.4f))

            ExposedDropdownMenuBox(
                expanded = expandedArea,
                onExpandedChange = { expandedArea = it },
                modifier = Modifier.weight(0.6f)
            ) {
                OutlinedTextField(
                    value = selectedArea,
                    onValueChange = {},
                    readOnly = true,
                    shape = RoundedCornerShape(16.dp),
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedArea)
                    },
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth()
                )

                ExposedDropdownMenu(
                    expanded = expandedArea,
                    onDismissRequest = { expandedArea = false }) {
                    areaOptions.forEach { option ->
                        DropdownMenuItem(
                            text = { Text(text = option) },
                            onClick = {
                                selectedArea = option
                                expandedArea = false
                            }
                        )
                    }
                }
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Prioritas", modifier = Modifier.weight(0.4f))

            ExposedDropdownMenuBox(
                expanded = expandedPriority,
                onExpandedChange = { expandedPriority = it },
                modifier = Modifier.weight(0.6f)
            ) {
                OutlinedTextField(
                    value = selectedPriority,
                    onValueChange = {},
                    readOnly = true,
                    shape = RoundedCornerShape(16.dp),
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedPriority)
                    },
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth()
                )

                ExposedDropdownMenu(
                    expanded = expandedPriority,
                    onDismissRequest = { expandedPriority = false }) {
                    areaOptions.forEach { option ->
                        DropdownMenuItem(
                            text = { Text(text = option) },
                            onClick = {
                                selectedPriority = option
                                expandedPriority = false
                            }
                        )
                    }
                }
            }
        }



        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Tipe Tiket", fontFamily = fontFamily, modifier = Modifier.weight(0.4f))

            ExposedDropdownMenuBox(
                expanded = expandedTypeTicket,
                onExpandedChange = { expandedTypeTicket = it },
                modifier = Modifier.weight(0.6f)
            ) {
                OutlinedTextField(
                    value = selectedTypeTicket,
                    onValueChange = {},
                    readOnly = true,
                    shape = RoundedCornerShape(16.dp),
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedTypeTicket)
                    },
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth()
                )

                ExposedDropdownMenu(
                    expanded = expandedTypeTicket,
                    onDismissRequest = { expandedTypeTicket = false }) {
                    typeTicketOptions.forEach { option ->
                        DropdownMenuItem(
                            text = { Text(text = option) },
                            onClick = {
                                selectedTypeTicket = option
                                expandedTypeTicket = false
                            }
                        )
                    }
                }
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            Text(
                text = "Description",
                fontFamily = fontFamily,
                modifier = Modifier
                    .weight(0.4f)
                    .padding(top = 24.dp)
            )

            InputText(
                value = description.value,
                onChange = { newValue ->
                    description = description.copy(
                        value = newValue,
                        isError = newValue.isEmpty()
                    )
                },
                label = "",
                minLines = 7,
                singleLine = false,
                isError = description.isError,
                trailingIcon = {
                    if (description.value.isNotEmpty()) {
                        IconButton(onClick = { description = description.copy(value = "") }) {
                            Icon(imageVector = Icons.Outlined.Clear, contentDescription = "")
                        }
                    }
                },
                supportingText = {
                    if (description.isError) {
                        Text(text = "Description cannot be empty", fontFamily = fontFamily)
                    }
                },
                modifier = Modifier.weight(0.6f)
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = { /*TODO*/ },
            shape = RoundedCornerShape(16.dp),
            contentPadding = PaddingValues(14.dp),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = "Submit",
                fontFamily = fontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 18.sp
            )
        }
    }
}

@Preview
@Composable
private fun AddTicketScreenPreview() {
    TicketSystemTheme {
        AddTicketContent()
    }
}

@Preview(device = "spec:width=411dp,height=891dp,dpi=420,isRound=false,chinSize=0dp,orientation=landscape")
@Composable
private fun AddTicketScreenLandscapePreview() {
    TicketSystemTheme {
        AddTicketContent()
    }
}