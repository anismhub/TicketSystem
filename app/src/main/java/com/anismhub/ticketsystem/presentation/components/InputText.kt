package com.anismhub.ticketsystem.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.anismhub.ticketsystem.presentation.theme.MyTypography
import com.anismhub.ticketsystem.presentation.theme.fontFamily
import java.time.Instant
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

@Composable
fun InputText(
    value: String,
    onChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String = "",
    isError: Boolean = false,
    minLines: Int = 1,
    singleLine: Boolean = true,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOption: KeyboardOptions = KeyboardOptions.Default,
    placeholder: @Composable () -> Unit = {},
    trailingIcon: @Composable () -> Unit = {},
    supportingText: @Composable () -> Unit = {},
    readonly: Boolean = false,
    enabled: Boolean = true
) {
    OutlinedTextField(
        value = value,
        onValueChange = onChange,
        singleLine = singleLine,
        textStyle = TextStyle(
            fontFamily = fontFamily
        ),
        minLines = minLines,
        shape = RoundedCornerShape(16.dp),
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOption,
        isError = isError,
        placeholder = placeholder,
        trailingIcon = trailingIcon,
        supportingText = supportingText,
        readOnly = readonly,
        enabled = enabled,
        label = { Text(text = label, fontFamily = fontFamily) },
        modifier = modifier
            .fillMaxWidth()
    )
}

@Composable
fun InputTextWithLabel(
    title: String,
    initialTextState: String,
    modifier: Modifier = Modifier,
    minLines: Int = 1,
    singleLine: Boolean = true,
    enabled: Boolean = true,
    keyboardOption: KeyboardOptions = KeyboardOptions.Default,
    trailingIcon: @Composable () -> Unit = {}
) {
    var textState by remember { mutableStateOf(initialTextState) }
    var isError by remember { mutableStateOf(false) }

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = title, modifier = Modifier.weight(0.4f))
        InputText(
            value = textState,
            onChange = { newValue ->
                textState = newValue
                isError = newValue.isEmpty()
            },
            label = "",
            isError = isError,
            minLines = minLines,
            singleLine = singleLine,
            keyboardOption = keyboardOption,
            trailingIcon = {
                trailingIcon()
            },
            supportingText = {
                if (isError) {
                    Text(text = "$title harus diisi", style = MyTypography.labelSmall)
                }
            },
            enabled = enabled,
            modifier = Modifier.weight(0.6f)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownMenuWithLabel(
    title: String,
    selectedValue: String,
    options: List<String>,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    var selected by remember { mutableStateOf(selectedValue) }
    var isError by remember { mutableStateOf(false) }

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = title, modifier = Modifier.weight(0.4f))

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = it },
            modifier = Modifier.weight(0.6f)
        ) {
            OutlinedTextField(
                value = selected,
                onValueChange = { newValue ->
                    isError = newValue.isEmpty()
                },
                readOnly = true,
                shape = RoundedCornerShape(16.dp),
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                },
                isError = isError,
                supportingText = {
                    if (isError) {
                        Text(text = "$title harus diisi", style = MyTypography.labelSmall)
                    }
                },
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }) {
                options.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(text = option) },
                        onClick = {
                            selected = option
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputTextWithDatePickerDialog(
    initialTextState: String,
    modifier: Modifier = Modifier
) {
    var textState by remember { mutableStateOf(initialTextState) }
    var showDatePicker by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()
    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    var showError by remember { mutableStateOf(false) }

    InputText(
        value = textState,
        onChange = { newValue ->
            textState = newValue
            showError = try {
                LocalDate.parse(newValue, formatter)
                false // Valid date
            } catch (e: DateTimeParseException) {
                true // Invalid date
            }
        },
        isError = showError,
        placeholder = {
            Text(text = "DD/MM/YYYY")
        },
        trailingIcon = {
            IconButton(
                onClick = { showDatePicker = true },
                modifier = Modifier.padding(end = 8.dp)
            ) {
                Icon(imageVector = Icons.Default.CalendarMonth, contentDescription = "")
            }
        },
        supportingText = {
            if (showError) {
                Text(text = "Format tanggal tidak valid")
            }
        },
        modifier = modifier.fillMaxWidth()
    )

    if (showDatePicker) {
        DatePickerDialog(
            onDismissRequest = { /*TODO*/ },
            confirmButton = {
                TextButton(
                    onClick = {
                        val selectedDate = datePickerState.selectedDateMillis!!
                        val instant = Instant.ofEpochMilli(selectedDate)
                        textState = instant.atZone(java.time.ZoneId.systemDefault()).toLocalDate()
                            .format(formatter)
                        showDatePicker = false
                    }
                ) { Text("OK") }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showDatePicker = false
                    }
                ) { Text("Cancel") }
            },
        )
        {
            DatePicker(state = datePickerState)
        }
    }
}