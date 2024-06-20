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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.anismhub.ticketsystem.R
import com.anismhub.ticketsystem.presentation.theme.MyTypography
import com.anismhub.ticketsystem.presentation.theme.Yellow

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
                painter = painterResource(id = R.drawable.person),
                contentDescription = "User",
                modifier = Modifier.weight(1f)
            )
            DetailSectionCard(
                title = "Dibuat",
                subtitle = ticketCreatedAt,
                painter = painterResource(id = R.drawable.schedule),
                modifier = Modifier.weight(1f)
            )
            DetailSectionCard(
                title = "Terakhir diperbarui",
                subtitle = ticketUpdatedAt,
                painter = painterResource(id = R.drawable.update),
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
                painter = painterResource(id = R.drawable.location_on),
                modifier = Modifier.weight(1f)
            )
            DetailSectionCard(
                title = "Kategori",
                subtitle = ticketCategory,
                painter = painterResource(id = R.drawable.category),
                modifier = Modifier.weight(1f)
            )
            DetailSectionCard(
                title = "Prioritas",
                subtitle = ticketPriority,
                painter = painterResource(id = R.drawable.flag),
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun DetailSectionCard(
    title: String,
    subtitle: String,
    painter: Painter,
    modifier: Modifier = Modifier,
    contentDescription: String = ""
) {
    Card(
        modifier = modifier
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
        colors = if (subtitle == "Tinggi") {
            CardDefaults.cardColors(MaterialTheme.colorScheme.error)
        } else if (subtitle == "Sedang") {
            CardDefaults.cardColors(Yellow)
        } else {
            CardDefaults.cardColors()
        }
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Row(horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()) {
                Icon(painter = painter, contentDescription = contentDescription)
                Text(text = title, textAlign = TextAlign.Center, style = MyTypography.bodyMedium)
            }
            Text(text = subtitle, textAlign = TextAlign.Center, style = MyTypography.bodySmall)
        }
    }
}