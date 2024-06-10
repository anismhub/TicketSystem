package com.anismhub.ticketsystem.presentation.screen.tickets.detailticket

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.anismhub.ticketsystem.domain.model.DetailTicket
import com.anismhub.ticketsystem.presentation.common.teknisiOptions
import com.anismhub.ticketsystem.presentation.components.CustomDialog
import com.anismhub.ticketsystem.presentation.components.DetailCard
import com.anismhub.ticketsystem.presentation.components.DetailSectionCard
import com.anismhub.ticketsystem.presentation.components.InputText
import com.anismhub.ticketsystem.presentation.components.MyDropdownMenu
import com.anismhub.ticketsystem.presentation.theme.MyTypography
import com.anismhub.ticketsystem.utils.Resource
import com.anismhub.ticketsystem.utils.toDateTime

@Composable
fun DetailTicketScreen(
    ticketId: Int,
    modifier: Modifier = Modifier,
    viewModel: DetailTicketViewModel = hiltViewModel()
) {
    LaunchedEffect(ticketId) {
        viewModel.getTicketById(ticketId)
    }

    val detailTicket by viewModel.detailTicket.collectAsStateWithLifecycle()

    when (val result = detailTicket) {
        is Resource.Loading -> {
            Log.d("DetailTicket Loading", "Loading: ")
        }

        is Resource.Success -> {
            DetailTicketContent(
                data = result.data,
                modifier = modifier
            )
        }

        is Resource.Error -> {
            Log.d("DetailTicket Error", "Detail Error: ${result.error}: ")
        }

        else -> {}
    }
}

@Composable
fun DetailTicketContent(
    data: DetailTicket,
    modifier: Modifier = Modifier,
) {
    var replyText by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }
    var enteredText by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .padding(top = 16.dp)
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            colors = CardDefaults.cardColors(Color.Transparent),
            shape = RoundedCornerShape(16.dp),
            modifier = modifier.fillMaxWidth()
        ) {
            var selectedTeknisi by remember { mutableStateOf("") }
            var selectedTeknisiIndex by remember { mutableIntStateOf(0) }

            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "#${data.ticketId} ${data.ticketSubject}",
                    style = MyTypography.titleLarge.copy(fontWeight = FontWeight.SemiBold)
                )
                Text(
                    text = data.ticketStatus,
                    style = MyTypography.titleLarge,
                    modifier = Modifier.align(Alignment.End)
                )
                DetailCard(
                    ticketCreatedAt = data.ticketCreatedAt.toDateTime(),
                    ticketUpdatedAt = data.ticketUpdateAt.toDateTime(),
                    ticketCategory = data.ticketCategory,
                    userFullName = "Nama Karyawan",
                    departmentName = "Procurement",
                    ticketArea = data.ticketArea,
                    ticketPriority = data.ticketPriority
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "Deskripsi", style = MyTypography.titleMedium)
                Text(
                    text = data.ticketDescription,
                    textAlign = TextAlign.Justify,
                    minLines = 3,
                    modifier = Modifier
                        .border(
                            border = ButtonDefaults.outlinedButtonBorder,
                            shape = RoundedCornerShape(16.dp)
                        )
                        .fillMaxWidth()
                        .padding(8.dp)
                )
            }
        }
        Text(
            text = "Balasan", style = MyTypography.titleMedium, modifier = Modifier
                .align(Alignment.Start)
                .padding(top = 12.dp)
        )
        if (data.comments.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier.padding(horizontal = 24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(vertical = 8.dp),
            ) {
                items(data.comments, key = { it.commentId }) {
                    InputText(
                        value = it.commentContent,
                        onChange = {},
                        minLines = 5,
                        singleLine = false,
                        readonly = true
                    )
                }
            }
        }
        InputText(
            value = replyText,
            onChange = { newValue ->
                replyText = newValue
            },
            minLines = 5,
            singleLine = false,
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.Bottom,
        ) {
            Button(onClick = { /*TODO*/ }) {
                Text(text = "Perbarui")
            }
            Button(onClick = { showDialog = true }) {
                Text(text = "Tutup")
            }
        }
    }

    CustomDialog(
        showDialog = showDialog,
        onDismiss = { showDialog = false },
        title = "Resolusi Tiket",
        textInput = Pair("Resolusi") { enteredText = it }, // Add text input
        confirmButton = {
            Button(onClick = {
                // Use the enteredText value
                replyText = enteredText
                showDialog = false
            }) {
                Text("Tutup Tiket")
            }
        },
        dismissButton = { // Add a dismiss button
            Button(onClick = { showDialog = false }) {
                Text("Batal")
            }
        }
    )
}