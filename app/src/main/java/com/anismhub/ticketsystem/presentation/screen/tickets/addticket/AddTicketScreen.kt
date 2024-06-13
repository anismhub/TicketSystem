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
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.anismhub.ticketsystem.R
import com.anismhub.ticketsystem.presentation.common.areaOptions
import com.anismhub.ticketsystem.presentation.common.priorityOptions
import com.anismhub.ticketsystem.presentation.common.typeTicketOptions
import com.anismhub.ticketsystem.presentation.components.DropdownMenuWithLabel
import com.anismhub.ticketsystem.presentation.components.InputTextWithLabel
import com.anismhub.ticketsystem.presentation.theme.MyTypography
import com.anismhub.ticketsystem.presentation.theme.fontFamily
import com.anismhub.ticketsystem.utils.Resource

@Composable
fun AddTicketScreen(
    onNavUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: AddTicketViewModel = hiltViewModel()
) {
    val addTicket by viewModel.addTicket.collectAsStateWithLifecycle()
    var selectedArea by remember { mutableStateOf("") }
    var selectedAreaIndex by remember { mutableIntStateOf(0) }
    var selectedPriorityIndex by remember { mutableIntStateOf(0) }
    var selectedTypeTicketIndex by remember { mutableIntStateOf(0) }

    var selectedPriority by remember { mutableStateOf(priorityOptions[0]) }
    var selectedTypeTicket by remember { mutableStateOf("") }
    var subject by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    addTicket.let {
        if (!it.hasBeenHandled) {
            when (val result = it.getContentIfNotHandled()) {
                is Resource.Loading -> {
                    Toast.makeText(LocalContext.current, "Loading", Toast.LENGTH_SHORT).show()
                }
                is Resource.Success -> {
                    onNavUp()
                }

                is Resource.Error -> {
                    Toast.makeText(LocalContext.current, result.error, Toast.LENGTH_SHORT).show()
                }

                else -> {}
            }
        }
    }

    AddTicketContent(
        onNavUp = onNavUp,
        createTicket = {
            viewModel.addTicket(
                ticketSubject = subject,
                ticketDescription = description,
                ticketArea = selectedAreaIndex + 1,
                ticketPriority = selectedPriority,
                ticketCategory = selectedTypeTicketIndex + 1
            )
        },
        subjectValue = subject,
        onSubjectChange = { subject = it },
        areaValue = selectedArea,
        onAreaChange = { area, index ->
            selectedArea = area
            selectedAreaIndex = index
        },
        priorityValue = selectedPriority,
        onPriorityChange = { priority, index ->
            selectedPriority = priority
            selectedPriorityIndex = index
        },
        categoryValue = selectedTypeTicket,
        onCategoryChange = { typeTicket, index ->
            selectedTypeTicket = typeTicket
            selectedTypeTicketIndex = index
        },
        descriptionValue = description,
        onDescriptionChange = { description = it },
        modifier = modifier
    )

}

@Composable
fun AddTicketContent(
    onNavUp: () -> Unit,
    createTicket: () -> Unit,
    subjectValue: String,
    onSubjectChange: (String) -> Unit,
    areaValue: String,
    onAreaChange: (String, Int) -> Unit,
    priorityValue: String,
    onPriorityChange: (String, Int) -> Unit,
    categoryValue: String,
    onCategoryChange: (String, Int) -> Unit,
    descriptionValue: String,
    onDescriptionChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
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
                    painter = painterResource(id = R.drawable.round_arrow_back24),
                    contentDescription = "Kembali",
                )
            }
            Spacer(modifier = Modifier.width(46.dp))
            Text(
                text = "Buat Tiket Baru",
                style = MyTypography.headlineMedium.copy(fontWeight = FontWeight.Bold),
            )
        }
        InputTextWithLabel(title = "Subjek", value = subjectValue, onValueChange = onSubjectChange)
        DropdownMenuWithLabel(
            title = "Area", value = areaValue,
            onValueChange = onAreaChange,
            options = areaOptions
        )
        DropdownMenuWithLabel(
            title = "Prioritas",
            value = priorityValue,
            onValueChange = onPriorityChange,
            options = priorityOptions
        )
        DropdownMenuWithLabel(
            title = "Tipe Tiket", value = categoryValue,
            onValueChange = onCategoryChange,
            options = typeTicketOptions
        )
        InputTextWithLabel(
            title = "Deskripsi",
            value = descriptionValue,
            onValueChange = onDescriptionChange,
            minLines = 7,
            singleLine = false
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {
                createTicket()
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
