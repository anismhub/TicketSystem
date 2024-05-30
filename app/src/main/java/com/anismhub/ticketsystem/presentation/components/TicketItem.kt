package com.anismhub.ticketsystem.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.anismhub.ticketsystem.presentation.theme.MyTypography
import com.anismhub.ticketsystem.presentation.theme.TicketSystemTheme

@Composable
fun TicketItem(
    number: Int,
    title: String,
    date: String,
    priority: String,
    status: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "#$number", style = MyTypography.bodyLarge
                )
                Text(
                    text = date, style = MyTypography.bodySmall
                )
            }

            Text(
                text = title, style = MyTypography.bodyLarge.copy(fontWeight = FontWeight.Medium)
            )

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = priority, style = MyTypography.bodyMedium
                )

                Text(
                    text = status, style = MyTypography.bodyMedium
                )
            }
        }
    }
}

@Preview
@Composable
private fun TicketItemPreview() {
    TicketSystemTheme {
        TicketItem(1, "Title", "Date", "Level", "Status", {})
    }
}