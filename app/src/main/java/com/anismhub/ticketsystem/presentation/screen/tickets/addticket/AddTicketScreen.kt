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
import com.anismhub.ticketsystem.presentation.common.DropdownMenuState
import com.anismhub.ticketsystem.presentation.common.InputTextState
import com.anismhub.ticketsystem.presentation.common.areaOptions
import com.anismhub.ticketsystem.presentation.common.priorityOptions
import com.anismhub.ticketsystem.presentation.common.typeTicketOptions
import com.anismhub.ticketsystem.presentation.components.DropdownMenuWithLabel
import com.anismhub.ticketsystem.presentation.components.InputTextWithLabel
import com.anismhub.ticketsystem.presentation.theme.MyTypography
import com.anismhub.ticketsystem.presentation.theme.fontFamily
import com.anismhub.ticketsystem.utils.Resource
import com.anismhub.ticketsystem.utils.isInvalid

@Composable
fun AddTicketScreen(
    onNavUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: AddTicketViewModel = hiltViewModel()
) {
    val addTicket by viewModel.addTicket.collectAsStateWithLifecycle()
    var selectedArea by remember { mutableStateOf(DropdownMenuState("Pilih Area", 0)) }
    var selectedAreaIndex by remember { mutableIntStateOf(-1) }
    var selectedPriorityIndex by remember { mutableIntStateOf(-1) }
    var selectedTypeTicketIndex by remember { mutableIntStateOf(-1) }

    var selectedPriority by remember { mutableStateOf(DropdownMenuState("Pilih Prioritas", -1)) }
    var selectedTypeTicket by remember { mutableStateOf(DropdownMenuState("Pilih Tipe", 0)) }
    var subject by remember { mutableStateOf(InputTextState()) }
    var description by remember { mutableStateOf(InputTextState()) }

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
                ticketSubject = subject.value,
                ticketDescription = description.value,
                ticketArea = selectedArea.indexValue,
                ticketPriority = selectedPriority.text,
                ticketCategory = selectedTypeTicket.indexValue
            )
        },
        subject = subject,
        onSubjectChange = { subject = it },
        area = selectedArea,
        onAreaChange = { area, index ->
            selectedArea = selectedArea.copy(
                text = area,
                indexValue = index + 1,
                isError = false
            )
            selectedAreaIndex = index
        },
        onAreaError = { selectedArea = it },
        priority = selectedPriority,
        onPriorityChange = { priority, index ->
            selectedPriority = selectedPriority.copy(
                text = priority,
                indexValue = index,
                isError = false
            )
            selectedPriorityIndex = index
        },
        onPriorityError = { selectedPriority = it },
        category = selectedTypeTicket,
        onCategoryChange = { typeTicket, index ->
            selectedTypeTicket = selectedTypeTicket.copy(
                text = typeTicket,
                indexValue = index + 1,
                isError = false
            )
            selectedTypeTicketIndex = index
        },
        onCategoryError = { selectedTypeTicket = it },
        description = description,
        onDescriptionChange = { description = it },

        modifier = modifier
    )

}

@Composable
fun AddTicketContent(
    onNavUp: () -> Unit,
    createTicket: () -> Unit,
    subject: InputTextState,
    onSubjectChange: (InputTextState) -> Unit,
    area: DropdownMenuState,
    onAreaChange: (String, Int) -> Unit,
    onAreaError: (DropdownMenuState) -> Unit,
    priority: DropdownMenuState,
    onPriorityChange: (String, Int) -> Unit,
    onPriorityError: (DropdownMenuState) -> Unit,
    category: DropdownMenuState,
    onCategoryChange: (String, Int) -> Unit,
    onCategoryError: (DropdownMenuState) -> Unit,
    description: InputTextState,
    onDescriptionChange: (InputTextState) -> Unit,
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
        InputTextWithLabel(title = "Subjek", textState = subject, onValueChange = onSubjectChange,
            trailingIcon = {
                IconButton(onClick = {
                    onSubjectChange(
                        subject.copy(
                            value = "",
                            isError = true
                        )
                    )
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.close_24px),
                        contentDescription = ""
                    )
                }
            })
        DropdownMenuWithLabel(
            title = "Area", value = area.text,
            onValueChange = onAreaChange,
            options = areaOptions,
            isError = area.isError
        )
        DropdownMenuWithLabel(
            title = "Prioritas",
            value = priority.text,
            onValueChange = onPriorityChange,
            options = priorityOptions,
            isError = priority.isError
        )
        DropdownMenuWithLabel(
            title = "Tipe Tiket", value = category.text,
            onValueChange = onCategoryChange,
            options = typeTicketOptions,
            isError = category.isError
        )
        InputTextWithLabel(
            title = "Deskripsi",
            textState = description,
            onValueChange = onDescriptionChange,
            trailingIcon = {
                IconButton(onClick = {
                    onDescriptionChange(
                        description.copy(
                            value = "",
                            isError = true
                        )
                    )
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.close_24px),
                        contentDescription = ""
                    )
                }
            },
            minLines = 7,
            singleLine = false
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {
                when {
                    subject.isInvalid() -> onSubjectChange(subject.copy(isError = true))
                    description.isInvalid() -> onDescriptionChange(description.copy(isError = true))
                    area.isInvalid(1) -> onAreaError(area.copy(isError = true))
                    priority.isInvalid(0) -> onPriorityError(priority.copy(isError = true))
                    category.isInvalid(1) -> onCategoryError(category.copy(isError = true))
                    else -> {
                        createTicket()
                    }
                }
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
