package com.anismhub.ticketsystem.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.anismhub.ticketsystem.presentation.theme.MyTypography
import com.anismhub.ticketsystem.presentation.theme.TicketSystemTheme

@Composable
fun ReplyCard(
    name: String,
    date: String,
    content: String,
    modifier: Modifier = Modifier,
    containerColor: Color = Color.Unspecified,
) {
    Card(
        border = ButtonDefaults.outlinedButtonBorder,
        colors = CardDefaults.cardColors(
            containerColor = containerColor
        ),
        shape = RoundedCornerShape(16.dp),
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 12.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(text = name, style = MyTypography.titleLarge)
                Text(text = date, style = MyTypography.bodySmall)
            }
            Text(text = content, minLines = 3, style = MyTypography.bodyMedium)
        }
    }
}

@Preview
@Composable
private fun ReplyCardPreview() {
    TicketSystemTheme {
        ReplyCard(name = "Kepek", date = "2024/9/19", content = "Ini adalah konten")
    }
}