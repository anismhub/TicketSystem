package com.anismhub.ticketsystem.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
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
import com.anismhub.ticketsystem.presentation.common.InputTextState
import com.anismhub.ticketsystem.presentation.theme.fontFamily

@Composable
fun InputText(
    value: String,
    onChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    isError: Boolean = false,
    minLines: Int = 1,
    singleLine: Boolean = true,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOption: KeyboardOptions = KeyboardOptions.Default,
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
    initialTextState: InputTextState,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    keyboardOption: KeyboardOptions = KeyboardOptions.Default,
    trailingIcon: @Composable () -> Unit = {}
) {
    var textState by remember { mutableStateOf(initialTextState) }

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = title, modifier = Modifier.weight(0.4f))
        InputText(
            value = textState.value,
            onChange = { newValue ->
                textState = textState.copy(
                    value = newValue,
                    isError = newValue.isEmpty()
                )
            },
            label = "",
            isError = textState.isError,
            keyboardOption = keyboardOption,
            trailingIcon = {
                trailingIcon()
            },
            supportingText = {
                if (textState.isError) {
                    Text(text = "$title harus diisi")
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
    expandedValue: Boolean,
    options: List<String>,
    modifier: Modifier = Modifier
) {

    var expanded by remember { mutableStateOf(expandedValue) }
    var selectedValue by remember { mutableStateOf("") }

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
                value = selectedValue,
                onValueChange = {},
                readOnly = true,
                shape = RoundedCornerShape(16.dp),
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
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
                            selectedValue = option
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}