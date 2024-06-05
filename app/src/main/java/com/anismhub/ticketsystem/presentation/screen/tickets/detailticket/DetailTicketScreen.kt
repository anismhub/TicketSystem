package com.anismhub.ticketsystem.presentation.screen.tickets.detailticket

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.anismhub.ticketsystem.data.DataDummy
import com.anismhub.ticketsystem.presentation.common.teknisiOptions
import com.anismhub.ticketsystem.presentation.components.CustomDialog
import com.anismhub.ticketsystem.presentation.components.InputText
import com.anismhub.ticketsystem.presentation.components.MyDropdownMenu
import com.anismhub.ticketsystem.presentation.theme.MyTypography

@Composable
fun DetailTicketScreen() {

}

@Composable
fun DetailTicketContent(modifier: Modifier = Modifier) {
    var replyText by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }
    var enteredText by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .padding(top = 16.dp)
            .padding(horizontal = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(16.dp))
//        DetailTicketCard()
        Card(
            colors = CardDefaults.cardColors(Color.Transparent),
            shape = RoundedCornerShape(16.dp),
            modifier = modifier.fillMaxWidth()
        ) {
            var selectedTeknisi by remember { mutableStateOf("") }
            val dummyTicket = DataDummy.tickets[0]

            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "#1 ${dummyTicket.title}",
                    style = MyTypography.titleLarge.copy(fontWeight = FontWeight.SemiBold)
                )
                Text(text = dummyTicket.status, modifier = Modifier.align(Alignment.End))
                Row {
                    Spacer(modifier = Modifier.weight(0.6f))
                    MyDropdownMenu(
                        value = selectedTeknisi,
                        onValueChange = { selectedTeknisi = it },
                        options = teknisiOptions,
                        enabled = true,
                        modifier = Modifier.weight(0.4f))
                }

                Column(
                    modifier = Modifier
                        .border(
                            border = ButtonDefaults.outlinedButtonBorder,
                            shape = RoundedCornerShape(16.dp)
                        )
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceAround,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Card(
                            modifier = Modifier.padding(vertical = 8.dp),
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(MaterialTheme.colorScheme.tertiaryContainer)
                        ) {
                            Column(
                                verticalArrangement = Arrangement.spacedBy(12.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.padding(8.dp)
                            ) {
                                Text(text = "Dibuat")
                                Text(dummyTicket.date)
                            }
                        }
                        Card(
                            modifier = Modifier.padding(vertical = 8.dp),
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(MaterialTheme.colorScheme.tertiaryContainer)
                        ) {
                            Column(
                                verticalArrangement = Arrangement.spacedBy(12.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.padding(8.dp)
                            ) {
                                Text(text = "Terakhir diperbarui")
                                Text(dummyTicket.date)
                            }
                        }
                        Card(
                            modifier = Modifier.padding(vertical = 8.dp),
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(MaterialTheme.colorScheme.tertiaryContainer)
                        ) {
                            Column(
                                verticalArrangement = Arrangement.spacedBy(12.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.padding(8.dp)
                            ) {
                                Text(text = "Kategori")
                                Text("Troubleshoot")
                            }
                        }
                    }

                    Row(
                        horizontalArrangement = Arrangement.SpaceAround,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Card(
                            modifier = Modifier.padding(vertical = 8.dp),
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(MaterialTheme.colorScheme.tertiaryContainer)
                        ) {
                            Column(
                                verticalArrangement = Arrangement.spacedBy(12.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.padding(8.dp)
                            ) {
                                Text(text = "Nama Karyawan")
                                Text("Departemen")
                            }
                        }
                        Card(
                            modifier = Modifier.padding(vertical = 8.dp),
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(MaterialTheme.colorScheme.tertiaryContainer)
                        ) {
                            Column(
                                verticalArrangement = Arrangement.spacedBy(12.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.padding(8.dp)
                            ) {
                                Text(text = "Area")
                                Text("Admin Building")
                            }
                        }
                        Card(
                            modifier = Modifier.padding(vertical = 8.dp),
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(MaterialTheme.colorScheme.tertiaryContainer)
                        ) {
                            Column(
                                verticalArrangement = Arrangement.spacedBy(12.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.padding(8.dp)
                            ) {
                                Text(text = "Prioritas")
                                Text(dummyTicket.priority)
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "Deskripsi", style = MyTypography.titleMedium)
                Text(
                    text = dummyTicket.description,
                    textAlign = TextAlign.Justify,
                    minLines = 3,
                    modifier = Modifier
                        .border(
                            border = ButtonDefaults.outlinedButtonBorder,
                            shape = RoundedCornerShape(16.dp)
                        )
                        .fillMaxWidth()
                        .padding(8.dp)
                )
            }
        }
//        DetailTicketCard()
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
            Button(onClick = { showDialog = true }) {
                Text(text = "Tutup")
            }
        }
    }

    CustomDialog(
        showDialog = showDialog,
        onDismiss = { showDialog = false },
        title = "Enter Text",
        textInput = Pair("Your Input", { enteredText = it }), // Add text input
        confirmButton = {
            Button(onClick = {
                // Use the enteredText value
                println("Entered Text: $enteredText")
                showDialog = false
            }) {
                Text("Save")
            }
        },
        dismissButton = { // Add a dismiss button
            Button(onClick = { showDialog = false }) {
                Text("Cancel")
            }
        }
    )
}