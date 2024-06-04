package com.anismhub.ticketsystem.presentation.components

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.anismhub.ticketsystem.presentation.theme.MyTypography
import com.anismhub.ticketsystem.presentation.theme.fontFamily
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

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
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    minLines: Int = 1,
    singleLine: Boolean = true,
    enabled: Boolean = true,
    keyboardOption: KeyboardOptions = KeyboardOptions.Default,
    trailingIcon: @Composable () -> Unit = {}
) {
    var isError by remember { mutableStateOf(false) }

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = title, modifier = Modifier.weight(0.35f))
        InputText(
            value = value,
            onChange = { newValue ->
                onValueChange(newValue)
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
            modifier = Modifier.weight(0.65f)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyDropdownMenu(
    value: String?,
    onValueChange: (String) -> Unit,
    options: List<String>,
    modifier: Modifier = Modifier,
    enabled: Boolean,
    supportingText: @Composable () -> Unit = {}
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedIndex by remember {
        mutableIntStateOf(options.indexOf(value))
    }
    val isError by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = enabled },
        modifier = modifier
    ) {
        OutlinedTextField(
            value = value ?: "",
            onValueChange = { },
            readOnly = true,
            shape = RoundedCornerShape(16.dp),
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            isError = isError,
            supportingText = {
                if (isError) {
                    supportingText()
                }
            },
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth()
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }) {
            options.forEachIndexed { index, option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        selectedIndex = index
                        onValueChange(option)
                        expanded = false
                    }
                )
            }
        }
    }
}

@Composable
fun DropdownMenuWithLabel(
    title: String,
    value: String,
    onValueChange: (String) -> Unit,
    options: List<String>,
    modifier: Modifier = Modifier
) {
    val isError by remember { mutableStateOf(false) }

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = title, modifier = Modifier.weight(0.35f))

        MyDropdownMenu(
            value = value,
            onValueChange = onValueChange,
            options = options,
            enabled = true,
            modifier = Modifier.weight(0.65f),
            supportingText = {
                if (isError) {
                    Text(text = "$title harus diisi", style = MyTypography.labelSmall)
                }
            },
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReusableDatePicker(
    modifier: Modifier = Modifier,
    initialDate: LocalDate? = null,
    minDateAllowed: LocalDate = LocalDate.now().minusYears(2),
    onDateSelected: (LocalDate) -> Unit
) {
    var showDatePicker by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()

    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    var dateState by remember { mutableStateOf(initialDate?.format(formatter) ?: "") }
    var isError by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalAlignment = Alignment.Start
    ) {
        InputText(
            value = dateState,
            onChange = { dateState = it },
            placeholder = {
                Text(text = "DD/MM/YYYY")
            },
            isError = isError,
            trailingIcon = {
                IconButton(onClick = { showDatePicker = true }) {
                    Icon(
                        imageVector = Icons.Default.CalendarMonth,
                        contentDescription = "Select Date"
                    )
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        if (showDatePicker) {
            DatePickerDialog(
                onDismissRequest = { showDatePicker = false },
                confirmButton = {
                    TextButton(onClick = {
                        val selectedDateMillis = datePickerState.selectedDateMillis
                        if (selectedDateMillis != null) {
                            val instant = Instant.ofEpochMilli(selectedDateMillis)
                            val selectedDate = instant.atZone(ZoneOffset.UTC).toLocalDate()

                            if (selectedDate.isAfter(minDateAllowed)) {
                                onDateSelected(selectedDate)
                                dateState = selectedDate.format(formatter)
                                isError = false
                                showDatePicker = false
                            } else {
                                Toast.makeText(
                                    context,
                                    "Selected date must be after ${minDateAllowed.format(formatter)}",
                                    Toast.LENGTH_SHORT
                                ).show()
                                isError = true
                            }
                        }
                    }) { Text("OK") }
                },
                dismissButton = {
                    TextButton(onClick = { showDatePicker = false }) { Text("Cancel") }
                }
            ) {
                DatePicker(state = datePickerState)
            }
        }
    }
}