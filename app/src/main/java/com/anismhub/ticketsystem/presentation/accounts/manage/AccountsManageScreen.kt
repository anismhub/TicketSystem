package com.anismhub.ticketsystem.presentation.accounts.manage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.anismhub.ticketsystem.presentation.theme.MyTypography

@Composable
fun AccountManageScreen(modifier: Modifier = Modifier) {

}

@Composable
fun AccountManageContent(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .padding(top = 16.dp)
            .padding(horizontal = 12.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Kelola Pengguna",
            style = MyTypography.headlineMedium.copy(fontWeight = FontWeight.Bold)
        )

    }
}