package com.anismhub.ticketsystem.presentation.screen.settings.exportreport

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.anismhub.ticketsystem.presentation.components.InputTextWithDatePickerDialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExportReportScreen(modifier: Modifier = Modifier) {
    var dariState by remember { mutableStateOf("") }
    var hinggaState by remember { mutableStateOf("") }
    val datePickerState = rememberDatePickerState()
    var showDatePicker by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
            .padding(horizontal = 12.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Dari :")
        InputTextWithDatePickerDialog(initialTextState = dariState)
        Text(text = "Hingga :")
        InputTextWithDatePickerDialog(initialTextState = hinggaState)

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

@Composable
@Preview
fun DatePickerSample() {
    ExportReportScreen()
}


