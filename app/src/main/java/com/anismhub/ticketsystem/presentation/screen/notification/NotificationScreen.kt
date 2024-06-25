package com.anismhub.ticketsystem.presentation.screen.notification

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.anismhub.ticketsystem.domain.model.NotificationData
import com.anismhub.ticketsystem.presentation.components.NotificationCard
import com.anismhub.ticketsystem.presentation.theme.MyTypography
import com.anismhub.ticketsystem.utils.Resource
import com.anismhub.ticketsystem.utils.toDateTime

@Composable
fun NotificationScreen(
    navigateToDetail: (title: String, ticketId: Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: NotificationViewModel = hiltViewModel()
) {
    val notificationData by viewModel.notification.collectAsStateWithLifecycle()
    var listNotification by remember { mutableStateOf(emptyList<NotificationData>()) }

    var isLoading by remember { mutableStateOf(false) }


    when (val result = notificationData) {
        is Resource.Loading -> {
            Log.d("Loading", "Notification Loading : $result")
            isLoading = true
        }

        is Resource.Success -> {
            listNotification = result.data.data
            isLoading = false
        }

        is Resource.Error -> {
            Log.d("Error", "Notification Error: ${result.error}")
            isLoading = false
        }

        else -> {}
    }

    NotificationContent(
        data = listNotification,
        navigateToDetail = {
            navigateToDetail("Detail Tiket", it)
        },
        modifier = modifier,
        isLoading = isLoading
    )
}

@Composable
fun NotificationContent(
    data: List<NotificationData>,
    navigateToDetail: (ticketId: Int) -> Unit,
    modifier: Modifier = Modifier,
    isLoading: Boolean = false
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
        Column(
            modifier = modifier.padding(start = 24.dp, end = 24.dp, top = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Notifikasi",
                style = MyTypography.headlineMedium.copy(fontWeight = FontWeight.Bold)
            )
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(bottom = 24.dp),
                modifier = Modifier
                    .padding(top = 16.dp)
            ) {
                items(data, key = { it.notificationId }) {
                    NotificationCard(
                        message = it.notificationContent,
                        date = it.notificationCreateAt.toDateTime(),
                        onClick = {
                            navigateToDetail(it.notificationTicket)
                        }
                    )
                }
            }
        }
    }
}