package com.anismhub.ticketsystem.presentation.screen.tickets.detailticket

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.anismhub.ticketsystem.R
import com.anismhub.ticketsystem.domain.model.DetailTicketData
import com.anismhub.ticketsystem.domain.model.TechProfileData
import com.anismhub.ticketsystem.presentation.components.CustomDialog
import com.anismhub.ticketsystem.presentation.components.DetailCard
import com.anismhub.ticketsystem.presentation.components.InputText
import com.anismhub.ticketsystem.presentation.components.MyDropdownMenuTech
import com.anismhub.ticketsystem.presentation.components.ReplyCard
import com.anismhub.ticketsystem.presentation.theme.MyTypography
import com.anismhub.ticketsystem.utils.Resource
import com.anismhub.ticketsystem.utils.toDateTime

@Composable
fun DetailTicketScreen(
    ticketId: Int,
    onNavUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DetailTicketViewModel = hiltViewModel()
) {
    LaunchedEffect(ticketId) {
        viewModel.getTicketById(ticketId)
    }

    val localProfile by viewModel.localProfileData
    val techUsers by viewModel.techUsers.collectAsStateWithLifecycle()
    val detailTicket by viewModel.detailTicket.collectAsStateWithLifecycle()
    val assignTicket by viewModel.assignTicket.collectAsStateWithLifecycle()
    val comment by viewModel.comments.collectAsStateWithLifecycle()
    val closeTicket by viewModel.closeTicket.collectAsStateWithLifecycle()

    var detailData by remember {
        mutableStateOf(
            DetailTicketData(
                1, "", "", "", "",
                "", "", "", "", "2024-06-25T06:31:47.509Z",
                "2024-06-25T06:31:47.509Z", "", emptyList(), emptyList()
            )
        )
    }

    var isLoading by remember { mutableStateOf(false) }
    var isOpen by remember { mutableStateOf(false) }
    var isClosed by remember { mutableStateOf(false) }
    var isAssigned by remember { mutableStateOf(false) }
    var isKaryawan by remember { mutableStateOf(false) }
    var isAdmin by remember { mutableStateOf(false) }

    var listTech by remember { mutableStateOf(emptyList<TechProfileData>()) }

    when (val resultTech = techUsers) {
        is Resource.Loading -> {
            Log.d("TechUsers Loading", "Loading: ")
        }

        is Resource.Success -> {
            Log.d("TechUsers Success", "Success: ${resultTech.data.data}: ")
            listTech = resultTech.data.data
        }

        is Resource.Error -> {
            Log.d("TechUsers Error", "Error: ${resultTech.error}: ")
        }

        else -> {}
    }

    when (val resultData = detailTicket) {
        is Resource.Loading -> {
            Log.d("DetailTicket Loading", "Loading..")
            isLoading = true
        }

        is Resource.Success -> {
            detailData = resultData.data.data
            isOpen = resultData.data.data.ticketStatus == "Open"
            isClosed = resultData.data.data.ticketStatus == "Closed"
            isAssigned = resultData.data.data.ticketAssignedTo != null
            isKaryawan = localProfile!!.userRole == "Karyawan"
            isAdmin = localProfile!!.userRole == "Administrator"
            isLoading = false
        }

        is Resource.Error -> {
            Log.d("DetailTicket Error", "Detail Error: ${resultData.error}: ")
            isLoading = false
        }

        else -> {}
    }

    assignTicket.let {
        when (val unhandled = it.getContentIfNotHandled()) {
            is Resource.Loading -> {
                Log.d("Comment Loading", "Loading: ")
                isLoading = true
            }

            is Resource.Success -> {
                onNavUp()
                Log.d("Comment Success", "Success: ${unhandled.data}: ")
                isLoading = false
            }

            is Resource.Error -> {
                Log.d("Comment Error", "Error: ${unhandled.error}: ")
                isLoading = false
            }

            else -> {}
        }
    }

    comment.let {
        if (!it.hasBeenHandled) {
            when (val unhandled = it.getContentIfNotHandled()) {
                is Resource.Loading -> {
                    Log.d("Comment Loading", "Loading: ")
                    isLoading = true
                }

                is Resource.Success -> {
                    viewModel.getTicketById(ticketId)
                    Log.d("Comment Success", "Success: ${unhandled.data}: ")
                    isLoading = false
                }

                is Resource.Error -> {
                    Log.d("Comment Error", "Error: ${unhandled.error}: ")
                    isLoading = false
                }

                else -> {}
            }
        }

    }

    closeTicket.let {
        if (!it.hasBeenHandled) {
            when (val unhandled = it.getContentIfNotHandled()) {
                is Resource.Loading -> {
                    Log.d("Close Ticket Loading", "Loading: ")
                    isLoading = true
                }

                is Resource.Success -> {
                    onNavUp()
                    Log.d("Close Ticket Success", "Success: ${unhandled.data}: ")
                    isLoading = false
                }

                is Resource.Error -> {
                    Log.d("Close Ticket Error", "Error: ${unhandled.error}: ")
                    isLoading = false
                }

                else -> {}
            }
        }
    }
    DetailTicketContent(
        data = detailData, listTech = listTech, isOpen = isOpen,
        isClosed = isClosed, isAssigned = isAssigned, isKaryawan = isKaryawan,
        isAdmin = isAdmin, isLoading = isLoading,
        assignTicket = {
            if (it != 0) viewModel.assignTicket(ticketId, it)
        },
        addComment = {
            viewModel.addComment(ticketId, it)
        },
        addResolution = {
            viewModel.closeTicket(ticketId, it)
        },
        modifier = modifier
    )
}

@Composable
fun DetailTicketContent(
    data: DetailTicketData,
    listTech: List<TechProfileData>,
    assignTicket: (Int) -> Unit,
    addComment: (String) -> Unit,
    addResolution: (String) -> Unit,
    modifier: Modifier = Modifier,
    isOpen: Boolean = false,
    isClosed: Boolean = false,
    isAssigned: Boolean = false,
    isKaryawan: Boolean = false,
    isAdmin: Boolean = false,
    isLoading: Boolean = false,
) {
    var replyText by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }
    var enteredText by remember { mutableStateOf("") }
    var selectedTeknisi by remember { mutableStateOf<TechProfileData?>(null) }

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
        Column(
            modifier = modifier
                .padding(top = 16.dp)
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                colors = CardDefaults.cardColors(Color.Transparent),
                shape = RoundedCornerShape(16.dp),
                modifier = modifier.fillMaxWidth()
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
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
                    Row {
                        if (isOpen) {
                            Button(
                                onClick = { assignTicket(selectedTeknisi?.userId ?: -1) },
                                enabled = selectedTeknisi?.userId != null,
                                modifier = Modifier.weight(0.3f)
                            ) {
                                Text(text = "Tugaskan Teknisi", textAlign = TextAlign.Center)
                            }
                        }
                        Spacer(modifier = Modifier.weight(0.1f))
                        if (isAdmin) {
                            if (isAssigned || isClosed) {
                                Row(
                                    horizontalArrangement = Arrangement.End,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.engineer),
                                        contentDescription = "Teknisi"
                                    )
                                    Spacer(modifier = Modifier.width(12.dp))
                                    Text(
                                        text = data.ticketAssignedTo ?: "Belum Ada Teknisi",
                                        style = MyTypography.titleLarge
                                    )
                                }
                            } else {
                                MyDropdownMenuTech(
                                    value = selectedTeknisi?.userFullName ?: "Pilih Teknisi",
                                    onValueChange = { selectedTeknisi = it },
                                    listTech = listTech,
                                    modifier = Modifier.weight(0.5f)
                                )
                            }
                        } else {
                            Text(text = data.ticketAssignedTo ?: "Belum ada teknisi")
                        }
                    }
                    DetailCard(
                        ticketCreatedAt = data.ticketCreatedAt.toDateTime(),
                        ticketUpdatedAt = data.ticketUpdateAt.toDateTime(),
                        ticketCategory = data.ticketCategory,
                        userFullName = data.ticketCreatedBy,
                        departmentName = data.ticketDepartmentBy,
                        ticketArea = data.ticketArea,
                        ticketPriority = data.ticketPriority
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = "Deskripsi", style = MyTypography.titleMedium)
                    Text(
                        text = data.ticketDescription,
                        textAlign = TextAlign.Justify,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp)
                    )
                }
            }
            Text(
                text = "Balasan", style = MyTypography.titleMedium,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(top = 12.dp)
            )
            if (data.comments.isNotEmpty()) {
                data.comments.forEach {
                    ReplyCard(
                        name = it.commentName,
                        date = it.commentTime.toDateTime(),
                        content = it.commentContent,
                        painter = if (it.commentUserRole == "Karyawan") painterResource(id = R.drawable.person) else painterResource(
                            id = R.drawable.engineer
                        )
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                }
            }
            if (data.resolution.isNotEmpty()) {
                data.resolution.forEach {
                    ReplyCard(
                        name = it.resolutionResolvedBy,
                        date = it.resolutionResolvedAt.toDateTime(),
                        content = it.resolutionContent,
                        containerColor = Color(0xFFD8EFD3)
                    )
                }
            }

            if (!isClosed) {
                InputText(
                    value = replyText,
                    onChange = { newValue ->
                        replyText = newValue
                    },
                    trailingIcon = {
                        if (replyText.isNotEmpty()) {
                            IconButton(onClick = { replyText = "" }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.close_24px),
                                    contentDescription = ""
                                )
                            }
                        }
                    },
                    minLines = 5,
                    singleLine = false,
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 24.dp),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.Bottom,
                ) {
                    Button(
                        onClick = {
                            if (replyText.trim().isNotEmpty()) {
                                addComment(replyText)
                            }
                        },
                    ) {
                        Text(text = "Balas Pesan", textAlign = TextAlign.Center)
                    }
                    Spacer(modifier = Modifier.width(3.dp))
                    if (!isKaryawan) {
                        Spacer(modifier = Modifier.width(3.dp))
                        Button(
                            onClick = { showDialog = true },
                        ) {
                            Text(text = "Tutup Tiket", textAlign = TextAlign.Center)
                        }
                    }
                }
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
                addResolution(enteredText)
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