package com.anismhub.ticketsystem.presentation.screen.settings

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
import androidx.hilt.navigation.compose.hiltViewModel
import com.anismhub.ticketsystem.presentation.components.ProfilCard
import com.anismhub.ticketsystem.presentation.components.SettingsMenu
import com.anismhub.ticketsystem.presentation.theme.MyTypography

@Composable
fun SettingsScreen() {

}

@Composable
fun SettingsContent(
    navigateToAuth: () -> Unit,
    navigateToManageAccount: (title : String) -> Unit,
    navigateToExport: (title : String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SettingsViewModel = hiltViewModel()
) {

    Column(
        modifier = modifier.padding(start = 24.dp, end = 24.dp, top = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Pengaturan",
            style = MyTypography.headlineMedium.copy(fontWeight = FontWeight.Bold)
        )
        ProfilCard(title = "Nama", subtitle = "Peran Pengguna", modifier = Modifier.padding(vertical = 36.dp))
        SettingsMenu(
            icon = Icons.Rounded.ManageAccounts,
            text = "Kelola Pengguna",
            onClick = { navigateToManageAccount("Kelola Pengguna") })
        SettingsMenu(
            icon = Icons.AutoMirrored.Rounded.OpenInNew,
            text = "Ekspor Laporan",
            onClick = { navigateToExport("Ekspor Laporan") })
        SettingsMenu(icon = Icons.Rounded.Close, text = "Sign Out", onClick = {
            viewModel.logout()
            navigateToAuth()
        })

    }
}



