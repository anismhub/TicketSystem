package com.anismhub.ticketsystem.presentation.components

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.anismhub.ticketsystem.R
import com.anismhub.ticketsystem.domain.model.TechProfileData
import com.anismhub.ticketsystem.presentation.common.InputTextState
import com.anismhub.ticketsystem.presentation.theme.MyTypography
import com.anismhub.ticketsystem.presentation.theme.fontFamily
import com.anismhub.ticketsystem.utils.toFormattedString
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneOffset

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
    textState: InputTextState,
    onValueChange: (InputTextState) -> Unit,
    modifier: Modifier = Modifier,
    errorText: String = "$title harus diisi",
    minLines: Int = 1,
    singleLine: Boolean = true,
    enabled: Boolean = true,
    keyboardOption: KeyboardOptions = KeyboardOptions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    trailingIcon: @Composable () -> Unit = {}
) {

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = title, modifier = Modifier.weight(0.35f))
        InputText(
            value = textState.value,
            onChange = { newValue ->
                onValueChange(
                    textState.copy(
                        value = newValue,
                        isError = newValue.isEmpty()
                    )
                )
            },
            label = "",
            isError = textState.isError,
            minLines = minLines,
            singleLine = singleLine,
            keyboardOption = keyboardOption,
            visualTransformation = visualTransformation,
            trailingIcon = {
                if (textState.value.isNotEmpty()) {
                    trailingIcon()
                }
            },
            supportingText = {
                if (textState.isError) {
                    Text(text = errorText, style = MyTypography.labelSmall)
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
    onValueChange: (String, Int) -> Unit,
    options: List<String>,
    modifier: Modifier = Modifier,
    enabled: Boolean,
    isError: Boolean,
    supportingText: @Composable () -> Unit = {}
) {
    var expanded by remember { mutableStateOf(false) }

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
                if (enabled) {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                }
            },
            isError = isError,
            supportingText = supportingText,
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth()
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }) {
            options.forEachIndexed { index, value ->
                DropdownMenuItem(
                    text = { Text(value) },
                    onClick = {
                        onValueChange(value, index)
                        expanded = false
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyDropdownMenuTech(
    value: String?,
    onValueChange: (TechProfileData) -> Unit,
    listTech: List<TechProfileData>,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    supportingText: @Composable () -> Unit = {}
) {
    var expanded by remember { mutableStateOf(false) }
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
                if (enabled) {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                }
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
            listTech.forEach { tech ->
                DropdownMenuItem(
                    text = { Text(tech.userFullName) },
                    onClick = {
                        onValueChange(tech)
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
    onValueChange: (String, Int) -> Unit,
    options: List<String>,
    modifier: Modifier = Modifier,
    isError: Boolean = false
) {

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
            isError = isError,
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
    dateState: InputTextState,
    onDateSelected: (InputTextState) -> Unit,
    modifier: Modifier = Modifier,
    errorText: String = "Tanggal harus diisi",
    minDateAllowed: LocalDate = LocalDate.now().minusYears(2),
) {
    var showDatePicker by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()

    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalAlignment = Alignment.Start
    ) {
        InputText(
            value = dateState.value,
            onChange = { newValue ->
                onDateSelected(
                    dateState.copy(
                        value = newValue,
                        isError = newValue.isEmpty()
                    )
                )
            },
            placeholder = {
                Text(text = "Pilih Tanggal")
            },
            isError = dateState.isError,
            trailingIcon = {
                IconButton(onClick = { showDatePicker = true }) {
                    Icon(
                        painter = painterResource(R.drawable.calendar_month_24px),
                        contentDescription = "Select Date"
                    )
                }
            },
            supportingText = {
                if (dateState.isError) {
                    Text(text = errorText, style = MyTypography.labelSmall)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .clickable { showDatePicker = true },
            readonly = true
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
                                onDateSelected(
                                    dateState.copy(
                                        value = selectedDate.toFormattedString(),
                                        isError = false
                                    )
                                )
                                showDatePicker = false
                            } else {
                                Toast.makeText(
                                    context,
                                    "Tanggal yang dipilih harus setelah $minDateAllowed",
                                    Toast.LENGTH_SHORT
                                ).show()
                                onDateSelected(dateState.copy(isError = true))
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