package com.anismhub.ticketsystem.presentation.screen.tickets.detailticket

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.anismhub.ticketsystem.R
import com.anismhub.ticketsystem.domain.model.DetailTicketData
import com.anismhub.ticketsystem.domain.model.TechProfileData
import com.anismhub.ticketsystem.presentation.common.InputTextState
import com.anismhub.ticketsystem.presentation.components.CustomDialog
import com.anismhub.ticketsystem.presentation.components.DetailCard
import com.anismhub.ticketsystem.presentation.components.InputText
import com.anismhub.ticketsystem.presentation.components.MyDropdownMenuTech
import com.anismhub.ticketsystem.presentation.components.ReplyCard
import com.anismhub.ticketsystem.presentation.theme.MyTypography
import com.anismhub.ticketsystem.utils.Resource
import com.anismhub.ticketsystem.utils.getImageUri
import com.anismhub.ticketsystem.utils.toDateTime

@Composable
fun DetailTicketScreen(
    ticketId: Int,
    onNavUp: () -> Unit,
    navigateToImageComment: (imageUrl: String) -> Unit,
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
    var imageUri: Uri? by remember {
        mutableStateOf(null)
    }

    var detailData by remember {
        mutableStateOf(
            DetailTicketData(
                1, "", "", "", "",
                "", "", "", "", "", "", "2024-06-25T06:31:47.509Z",
                "2024-06-25T06:31:47.509Z", "", emptyList()
            )
        )
    }

    var isLoading by remember { mutableStateOf(false) }
    var isOpen by remember { mutableStateOf(false) }
    var isClosed by remember { mutableStateOf(false) }
    var isAssigned by remember { mutableStateOf(false) }
    var isKaryawan by remember { mutableStateOf(false) }
    var isAdmin by remember { mutableStateOf(false) }
    var replyMessage by remember { mutableStateOf(InputTextState()) }

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
                    imageUri = null
                    viewModel.getTicketById(ticketId)
                    Log.d("Comment Success", "Success: ${unhandled.data}: ")
                    isLoading = false
                    replyMessage = InputTextState()
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
        isAdmin = isAdmin, isLoading = isLoading, replyMessage = replyMessage,
        onReplyChange = { replyMessage = it },
        assignTicket = {
            if (it != 0) viewModel.assignTicket(
                ticketId,
                it,
                "${detailData.ticketCategoryCode}-${detailData.ticketAreaCode}"
            )
        },
        addComment = {
            viewModel.addComment(
                ticketId,
                it,
                imageUri,
                "${detailData.ticketCategoryCode}-${detailData.ticketAreaCode}"
            )
        },
        closeTicket = {
            viewModel.closeTicket(
                ticketId,
                "${detailData.ticketCategoryCode}-${detailData.ticketAreaCode}"
            )
        },
        imageUri = imageUri,
        onImageUriChange = { imageUri = it },
        navigateToImageComment = navigateToImageComment,
        modifier = modifier
    )
}

@Composable
fun DetailTicketContent(
    data: DetailTicketData,
    replyMessage: InputTextState,
    onReplyChange: (InputTextState) -> Unit,
    listTech: List<TechProfileData>,
    assignTicket: (Int) -> Unit,
    addComment: (String) -> Unit,
    closeTicket: () -> Unit,
    imageUri: Uri?,
    onImageUriChange: (Uri?) -> Unit,
    navigateToImageComment: (imageUrl: String) -> Unit,
    modifier: Modifier = Modifier,
    isOpen: Boolean = false,
    isClosed: Boolean = false,
    isAssigned: Boolean = false,
    isKaryawan: Boolean = false,
    isAdmin: Boolean = false,
    isLoading: Boolean = false,
) {
    var showDialog by remember { mutableStateOf(false) }
    var selectedTeknisi by remember { mutableStateOf<TechProfileData?>(null) }
    var localImageUri: Uri? by remember { mutableStateOf(null) }
    val context = LocalContext.current
    val pickMediaLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            uri?.let {
                onImageUriChange(uri)
            }
        }

    val launcherIntentCamera = rememberLauncherForActivityResult(
        ActivityResultContracts.TakePicture()
    ) { isSuccess ->
        onImageUriChange(if (isSuccess) localImageUri else null)
    }

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
        Column(
            modifier = modifier
                .padding(horizontal = 6.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(
                    text = "#${data.ticketCategoryCode}-${data.ticketAreaCode}-${data.ticketId} ${data.ticketSubject}",
                    style = MyTypography.titleLarge.copy(fontWeight = FontWeight.SemiBold)
                )
                Text(
                    text = data.ticketStatus,
                    style = MyTypography.titleLarge,
                    modifier = Modifier.align(Alignment.End)
                )
                Row {
                    if (isAdmin) {
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
                        Spacer(modifier = Modifier.weight(1f))
                        Text(text = data.ticketAssignedTo ?: "Belum ada teknisi")
                    }
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
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(
                    text = "Deskripsi",
                    style = MyTypography.titleMedium,
                    modifier = Modifier.align(Alignment.Start)
                )
                Text(
                    text = data.ticketDescription,
                    textAlign = TextAlign.Justify,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            ) {
                if (data.comments.isNotEmpty()) {
                    Text(
                        text = "Balasan", style = MyTypography.titleMedium,
                        modifier = Modifier
                            .align(Alignment.Start)
                            .padding(top = 8.dp)
                    )
                    data.comments.sortedBy { it.commentTime }.forEach {
                        ReplyCard(
                            name = it.commentName,
                            date = it.commentTime.toDateTime(),
                            content = it.commentContent,
                            painter = if (it.commentUserRole == "Karyawan") painterResource(id = R.drawable.person) else
                                painterResource(id = R.drawable.engineer),
                            commentImage = it.commentImage,
                            navigateToImageComment = navigateToImageComment
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                    }
                }
            }
            if (!isClosed) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "Balas", style = MyTypography.titleMedium,
                            modifier = Modifier
                                .padding(top = 8.dp)
                        )
                        Row(
                            verticalAlignment = Alignment.Bottom,
                        ) {
                            TextButton(onClick = {
                                localImageUri = getImageUri(context)
                                launcherIntentCamera.launch(localImageUri!!)
                            }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.camera),
                                    contentDescription = "Ambil gambar dari Kamera"
                                )
                                Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
                                Text(text = "Kamera")
                            }

                            Spacer(modifier = Modifier.width(4.dp))

                            TextButton(onClick = {
                                pickMediaLauncher.launch(
                                    PickVisualMediaRequest(
                                        ActivityResultContracts.PickVisualMedia.ImageOnly
                                    )
                                )
                            }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.gallery),
                                    contentDescription = "Ambil gambar dari galeri"
                                )
                                Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
                                Text(text = "Galeri")
                            }
                        }
                    }

                    InputText(
                        value = replyMessage.value,
                        onChange = { newValue ->
                            onReplyChange(
                                replyMessage.copy(
                                    value = newValue
                                )
                            )
                        },
                        trailingIcon = {
                            if (replyMessage.value.isNotEmpty()) {
                                IconButton(onClick = {
                                    onReplyChange(
                                        replyMessage.copy(
                                            value = ""
                                        )
                                    )
                                }) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.close_24px),
                                        contentDescription = ""
                                    )
                                }
                            }
                        },
                        minLines = 4,
                        singleLine = false,
                        keyboardOption = KeyboardOptions(
                            imeAction = ImeAction.Done
                        )
                    )

                    imageUri?.let {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 8.dp, bottom = 12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = "Lampiran gambar", style = MyTypography.bodySmall)
                            IconButton(onClick = { onImageUriChange(null) }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.close_24px),
                                    contentDescription = "Hapus gambar"
                                )
                            }
                        }

                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 24.dp),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.Bottom,
                ) {
                    Button(
                        onClick = {
                            if (replyMessage.value.trim().isNotEmpty()) {
                                addComment(replyMessage.value)
                            }
                        },
                    ) {
                        Text(text = "Balas Pesan", textAlign = TextAlign.Center)
                    }
                    if (isKaryawan) {
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
        title = "Tutup Tiket",
        confirmButton = {
            Button(onClick = {
                closeTicket()
                showDialog = false
            }) {
                Text(text = "Tutup")
            }
        },
        dismissButton = {
            Button(onClick = { showDialog = false }) {
                Text("Batal")
            }
        }
    ) {
        Text(text = "Apakah anda yakin akan menutup tiket ini?")
    }
}