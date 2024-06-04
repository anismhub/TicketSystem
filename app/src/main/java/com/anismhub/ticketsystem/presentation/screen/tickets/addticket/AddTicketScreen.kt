package com.anismhub.ticketsystem.presentation.screen.tickets.addticket

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBackIos
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.anismhub.ticketsystem.presentation.common.areaOptions
import com.anismhub.ticketsystem.presentation.common.priorityOptions
import com.anismhub.ticketsystem.presentation.common.typeTicketOptions
import com.anismhub.ticketsystem.presentation.components.DropdownMenuWithLabel
import com.anismhub.ticketsystem.presentation.components.InputTextWithLabel
import com.anismhub.ticketsystem.presentation.theme.MyTypography
import com.anismhub.ticketsystem.presentation.theme.fontFamily

@Composable
fun AddTicketScreen(
) {

}

@Composable
fun AddTicketContent(
    onNavUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedArea by remember { mutableStateOf(areaOptions[0]) }
    var selectedPriority by remember { mutableStateOf(priorityOptions[0]) }
    var selectedTypeTicket by remember { mutableStateOf(typeTicketOptions[0]) }
    var subject by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 16.dp, bottom = 24.dp)
            .padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            IconButton(
                onClick = { onNavUp() },
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.ArrowBackIos,
                    contentDescription = "Kembali",
                )
            }
            Spacer(modifier = Modifier.width(46.dp))
            Text(
                text = "Buat Tiket Baru",
                style = MyTypography.headlineMedium.copy(fontWeight = FontWeight.Bold),
            )
        }
        InputTextWithLabel(title = "Subjek", value = subject, onValueChange = { newValue ->
            subject = newValue
        })
        DropdownMenuWithLabel(
            title = "Area", value = selectedArea,
            onValueChange = { selectedArea = it },
            options = areaOptions
        )
        DropdownMenuWithLabel(title = "Prioritas",
            value = selectedPriority,
            onValueChange = { selectedPriority = it },
            options = priorityOptions
        )
        DropdownMenuWithLabel(
            title = "Tipe Tiket", value = selectedTypeTicket,
            onValueChange = { selectedTypeTicket = it },
            options = typeTicketOptions
        )
        InputTextWithLabel(
            title = "Deskripsi",
            value = description,
            onValueChange = { description = it },
            minLines = 7,
            singleLine = false
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {
                Toast.makeText(
                    context,
                    "subjek: $subject,deskripsi: $description, area: $selectedArea, prioritas: $selectedPriority, tipe tiket: $selectedTypeTicket",
                    Toast.LENGTH_SHORT
                ).show()
            },
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
