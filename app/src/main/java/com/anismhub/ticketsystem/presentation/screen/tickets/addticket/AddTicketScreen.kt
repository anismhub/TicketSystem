package com.anismhub.ticketsystem.presentation.screen.tickets.addticket

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
import com.anismhub.ticketsystem.presentation.common.areaOptions
import com.anismhub.ticketsystem.presentation.common.priorityOptions
import com.anismhub.ticketsystem.presentation.common.typeTicketOptions
import com.anismhub.ticketsystem.presentation.components.DropdownMenuWithLabel
import com.anismhub.ticketsystem.presentation.components.InputText
import com.anismhub.ticketsystem.presentation.components.InputTextWithLabel
import com.anismhub.ticketsystem.presentation.theme.MyTypography
import com.anismhub.ticketsystem.presentation.theme.TicketSystemTheme
import com.anismhub.ticketsystem.presentation.theme.fontFamily

@Composable
fun AddTicketScreen(
) {

}

@Composable
fun AddTicketContent(
    modifier: Modifier = Modifier
) {
    var expandedArea by remember { mutableStateOf(false) }
    var selectedArea by remember { mutableStateOf(areaOptions[0]) }

    var expandedPriority by remember { mutableStateOf(false) }
    var selectedPriority by remember { mutableStateOf(priorityOptions[0]) }

    var expandedTypeTicket by remember { mutableStateOf(false) }
    var selectedTypeTicket by remember { mutableStateOf(typeTicketOptions[0]) }

    var subject by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Buat Tiket Baru",
            style = MyTypography.headlineMedium.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        InputTextWithLabel(title = "Subjek", initialTextState = subject)
        DropdownMenuWithLabel(title = "Area", selectedValue = selectedArea, options = areaOptions)
        DropdownMenuWithLabel(title = "Prioritas", selectedValue = selectedPriority, options = priorityOptions)
        DropdownMenuWithLabel(title = "Tipe Tiket", selectedValue = selectedTypeTicket, options = typeTicketOptions)
        InputTextWithLabel(title = "Deskripsi", initialTextState = description, minLines = 7, singleLine = false)

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