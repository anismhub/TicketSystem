package com.anismhub.ticketsystem.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun DetailCard(
    ticketCreatedAt: String,
    ticketUpdatedAt: String,
    ticketCategory: String,
    userFullName: String,
    departmentName: String,
    ticketArea: String,
    ticketPriority: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .border(
                border = ButtonDefaults.outlinedButtonBorder,
                shape = RoundedCornerShape(16.dp)
            )
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
        ) {
            DetailSectionCard(
                title = userFullName,
                subtitle = departmentName,
                modifier = Modifier.weight(1f)
            )
            DetailSectionCard(
                title = "Dibuat",
                subtitle = ticketCreatedAt,
                modifier = Modifier.weight(1f)
            )
            DetailSectionCard(
                title = "Terakhir diperbarui",
                subtitle = ticketUpdatedAt,
                modifier = Modifier.weight(1f)
            )
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
        ) {
            DetailSectionCard(
                title = "Area",
                subtitle = ticketArea,
                modifier = Modifier.weight(1f)
            )
            DetailSectionCard(
                title = "Kategori",
                subtitle = ticketCategory,
                modifier = Modifier.weight(1f)
            )
            DetailSectionCard(
                title = "Prioritas",
                subtitle = ticketPriority,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun DetailSectionCard(
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.tertiaryContainer)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(text = title, textAlign = TextAlign.Center)
            Text(text = subtitle, textAlign = TextAlign.Center)
        }
    }
}