package com.anismhub.ticketsystem.presentation.screen.settings.exportreport

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.anismhub.ticketsystem.presentation.components.ReusableDatePicker
import java.time.LocalDate

@Composable
fun ExportReportScreen(modifier: Modifier = Modifier) {

    var startDate by remember { mutableStateOf<LocalDate?>(null) }
    var endDate by remember { mutableStateOf<LocalDate?>(null) }

    val context = LocalContext.current

    Column(modifier = modifier.padding(16.dp)) {
        Text(text = "Tanggal Dari :")
        // Tanggal Dari DatePicker
        ReusableDatePicker(
            initialDate = LocalDate.now(), // Preselect a date 1 year ago
            onDateSelected = { startDate = it }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Tanggal Sampai :")
        // Tanggal Sampai DatePicker
        ReusableDatePicker(
            minDateAllowed = startDate ?: LocalDate.now().minusYears(2),
            onDateSelected = { endDate = it }
        )

    Spacer(modifier = Modifier.weight(1f))
    Button(
        onClick = {
            Toast.makeText(
                context,
                "tanggal dari : $startDate, tanggal sampai : $endDate",
                Toast.LENGTH_SHORT
            ).show()
        },
        shape = RoundedCornerShape(20),
        modifier = Modifier
            .align(Alignment.CenterHorizontally)
            .fillMaxWidth(0.6f)
            .padding(bottom = 8.dp)
    ) {
        Text(text = "Ekspor")
    }
}
}

@Composable
@Preview
fun DatePickerSample() {
    ExportReportScreen()
}


