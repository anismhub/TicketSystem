package com.anismhub.ticketsystem.presentation.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.automirrored.rounded.OpenInNew
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.ManageAccounts
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.anismhub.ticketsystem.presentation.theme.Typography

@Composable
fun SettingsScreen(modifier: Modifier = Modifier) {

}

@Composable
fun SettingsContent(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(start = 24.dp, end = 24.dp, top = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Pengaturan", style = Typography.titleLarge)
        ProfilCard(nama = "Nama", peran = "Peran Pengguna")
        SettingsMenu(
            icon = Icons.Rounded.ManageAccounts,
            text = "Kelola Pengguna",
            onClick = { /*TODO*/ })
        SettingsMenu(
            icon = Icons.AutoMirrored.Rounded.OpenInNew,
            text = "Ekspor Laporan",
            onClick = { /*TODO*/ })
        SettingsMenu(icon = Icons.Rounded.Close, text = "Sign Out", onClick = { /*TODO*/ })

    }
}

@Composable
fun ProfilCard(
    nama: String,
    peran: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 42.dp),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 22.dp, vertical = 16.dp),
        ) {
            Text(text = nama, style = Typography.displayMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = peran, style = Typography.headlineSmall)
        }

    }
}

@Composable
fun SettingsMenu(
    icon: ImageVector,
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = { onClick() },
        elevation = CardDefaults.cardElevation(0.dp),
        modifier = modifier
            .padding(bottom = 16.dp)
            .fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier
                .padding(vertical = 14.dp, horizontal = 18.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(34.dp)
                        .clip(shape = Shapes().medium)
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = "",
                        modifier = Modifier.padding(6.dp)
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))

                Text(text = text, style = Typography.bodyLarge)
            }
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                contentDescription = "",
                modifier = Modifier.size(16.dp)
            )
        }

    }
}