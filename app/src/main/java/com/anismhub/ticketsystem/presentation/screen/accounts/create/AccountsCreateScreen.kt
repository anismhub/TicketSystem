package com.anismhub.ticketsystem.presentation.screen.accounts.create

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.anismhub.ticketsystem.presentation.theme.MyTypography

@Composable
fun AccountsCreateScreen(modifier: Modifier = Modifier) {

}

@Composable
fun AccounstCreateContent(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .padding(top = 16.dp)
            .padding(horizontal = 12.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Buat Pengguna",
            style = MyTypography.headlineMedium.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}