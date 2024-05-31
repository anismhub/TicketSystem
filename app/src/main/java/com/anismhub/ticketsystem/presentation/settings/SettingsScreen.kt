package com.anismhub.ticketsystem.presentation.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.OpenInNew
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.ManageAccounts
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.anismhub.ticketsystem.presentation.components.ProfilCard
import com.anismhub.ticketsystem.presentation.components.SettingsMenu
import com.anismhub.ticketsystem.presentation.theme.MyTypography

@Composable
fun SettingsScreen(modifier: Modifier = Modifier) {

}

@Composable
fun SettingsContent(
    navigateToAuth: () -> Unit,
    navigateToManageAccount: () -> Unit,
    navigateToExport: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(start = 24.dp, end = 24.dp, top = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Pengaturan",
            style = MyTypography.headlineMedium.copy(fontWeight = FontWeight.Bold)
        )
        ProfilCard(nama = "Nama", peran = "Peran Pengguna")
        SettingsMenu(
            icon = Icons.Rounded.ManageAccounts,
            text = "Kelola Pengguna",
            onClick = { navigateToManageAccount() })
        SettingsMenu(
            icon = Icons.AutoMirrored.Rounded.OpenInNew,
            text = "Ekspor Laporan",
            onClick = { navigateToExport() })
        SettingsMenu(icon = Icons.Rounded.Close, text = "Sign Out", onClick = {
            navigateToAuth()
        })

    }
}



