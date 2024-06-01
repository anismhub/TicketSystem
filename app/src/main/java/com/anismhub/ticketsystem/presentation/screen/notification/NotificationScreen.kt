package com.anismhub.ticketsystem.presentation.screen.notification

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.anismhub.ticketsystem.data.DataDummy
import com.anismhub.ticketsystem.presentation.components.NotificationCard
import com.anismhub.ticketsystem.presentation.theme.MyTypography

@Composable
fun NotificationScreen(modifier: Modifier = Modifier) {

}

@Composable
fun NotificationContent(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(start = 24.dp, end = 24.dp, top = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Notifikasi", style = MyTypography.headlineMedium.copy(fontWeight = FontWeight.Bold))
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(bottom = 24.dp),
            modifier = Modifier
                .padding(top = 16.dp)
        ) {
            items(DataDummy.dummyTickets, key = { it.title }) {
                NotificationCard(message = "Ini adalah notifikasi dari "+ it.title, date = it.date)
            }
        }
    }
}