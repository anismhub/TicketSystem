package com.anismhub.ticketsystem.presentation.screen.tickets.detailticket

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
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
import com.anismhub.ticketsystem.presentation.common.InputTextState
import com.anismhub.ticketsystem.presentation.components.DetailTicketCard
import com.anismhub.ticketsystem.presentation.components.InputText
import com.anismhub.ticketsystem.presentation.theme.MyTypography

@Composable
fun DetailTicketScreen() {

}

@Composable
fun DetailTicketContent(modifier: Modifier = Modifier) {
    var replyText by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .padding(top = 16.dp)
            .padding(horizontal = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Detail Tiket",
            style = MyTypography.headlineMedium.copy(fontWeight = FontWeight.Bold)
        )
        Spacer(modifier = Modifier.height(16.dp))
        DetailTicketCard()
        Text(
            text = "Balasan", style = MyTypography.titleMedium, modifier = Modifier
                .align(Alignment.Start)
                .padding(top = 12.dp)
        )
        InputText(
            value = replyText,
            onChange = { newValue ->
                replyText = newValue
            },
            label = " ",
            minLines = 5,
            singleLine = false,
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.Bottom,
        ) {
            Button(onClick = { /*TODO*/ }) {
                Text(text = "Perbarui")
            }
            Button(onClick = { /*TODO*/ }) {
                Text(text = "Tutup")
            }
        }
    }
}