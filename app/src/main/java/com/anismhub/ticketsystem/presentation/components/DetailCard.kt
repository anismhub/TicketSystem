package com.anismhub.ticketsystem.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.anismhub.ticketsystem.R
import com.anismhub.ticketsystem.presentation.theme.LocalCustomColors
import com.anismhub.ticketsystem.presentation.theme.MyTypography

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
    Card(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background),
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.outline
        ),
        shape = RoundedCornerShape(16.dp),
    ) {
        Column {
            Row(
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            ) {
                DetailSectionCard(
                    title = userFullName,
                    subtitle = departmentName,
                    painter = painterResource(id = R.drawable.person),
                    contentDescription = "User",
                    subtitleIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.account_balance),
                            contentDescription = "Department"
                        )
                    },
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
}

@Composable
fun DetailSectionCard(
    title: String,
    subtitle: String,
    painter: Painter,
    modifier: Modifier = Modifier,
    contentDescription: String = "",
    subtitleIcon: @Composable () -> Unit = {}
) {
    val customColors = LocalCustomColors.current
    Card(
        modifier = modifier
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
        colors = when (subtitle) {
            "Tinggi" -> {
                CardDefaults.cardColors(MaterialTheme.colorScheme.error)
            }

            "Sedang" -> {
                CardDefaults.cardColors(customColors.warning.colorContainer)
            }

            else -> {
                CardDefaults.cardColors()
            }
        }
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 4.dp)
        ) {
            Row(
                verticalAlignment = Alignment.Top,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    painter = painter,
                    contentDescription = contentDescription,
                    modifier = Modifier
                )
                Text(
                    text = title,
                    textAlign = TextAlign.Center,
                    style = MyTypography.bodyMedium,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .weight(1f)
                )
            }
            Row(
                verticalAlignment = Alignment.Top,
                modifier = Modifier.fillMaxWidth()
            ) {
                subtitleIcon()
                Text(
                    text = subtitle,
                    textAlign = TextAlign.Center,
                    style = MyTypography.bodyMedium,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .weight(1f)
                )
            }
        }
    }
}

@Preview
@Composable
private fun PreviewDetail() {
    DetailCard(
        ticketCreatedAt = "Abc",
        ticketUpdatedAt = "Def",
        ticketCategory = "Ghg",
        userFullName = "User",
        departmentName = "Dep",
        ticketArea = "Area",
        ticketPriority = "Tinggi"
    )
}