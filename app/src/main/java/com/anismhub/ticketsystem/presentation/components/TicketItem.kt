package com.anismhub.ticketsystem.presentation.components

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
import androidx.compose.ui.unit.sp
import com.anismhub.ticketsystem.presentation.theme.TicketSystemTheme
import com.anismhub.ticketsystem.presentation.theme.fontFamily

@Composable
fun TicketItem(
    number: Int,
    title: String,
    date: String,
    priority: String,
    status: String,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = modifier
            .fillMaxWidth()
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "#$number",
                fontFamily = fontFamily,
                fontWeight = FontWeight.Medium
            )

            Text(
                text = "$title - $date",
                fontFamily = fontFamily,
                fontSize = 18.sp
            )

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = priority,
                    fontFamily = fontFamily
                )

                Text(
                    text = status,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Preview
@Composable
private fun TicketItemPreview() {
    TicketSystemTheme {
        TicketItem(1, "Title", "Date", "Level", "Status")
    }
}