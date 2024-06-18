package com.anismhub.ticketsystem.presentation.screen.notification

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
    modifier: Modifier = Modifier,
    viewModel: NotificationViewModel = hiltViewModel()
) {
    val notificationData by viewModel.notification.collectAsStateWithLifecycle()

    when (val result = notificationData) {
        is Resource.Loading -> {
            Log.d("Loading", "Notification Loading : $result")
        }

        is Resource.Success -> {
            NotificationContent(
                data = result.data.data,
                modifier = modifier
            )
        }

        is Resource.Error -> {
            Log.d("Error", "Notification Error: ${result.error}")
        }

        else -> {}
    }
}

@Composable
fun NotificationContent(
    data: List<NotificationData>,
    modifier: Modifier = Modifier
) {
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
                    date = it.notificationCreateAt.toDateTime()
                )
            }
        }
    }
}