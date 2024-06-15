package com.anismhub.ticketsystem.presentation.screen.settings

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.anismhub.ticketsystem.R
import com.anismhub.ticketsystem.domain.model.ProfileData
import com.anismhub.ticketsystem.presentation.components.ProfilCard
import com.anismhub.ticketsystem.presentation.components.SettingsMenu
import com.anismhub.ticketsystem.presentation.theme.MyTypography
import com.anismhub.ticketsystem.utils.Resource

@Composable
fun SettingsScreen(
    navigateToAuth: () -> Unit,
    navigateToManageAccount: (title: String) -> Unit,
    navigateToExport: (title: String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SettingsViewModel = hiltViewModel()
) {
    val profileDataState by viewModel.profileData.collectAsState()
    val localProfile by viewModel.localProfileData
    var profileData by remember {
        mutableStateOf(ProfileData(1, "", "", "", 1, "", ""))
    }

    when (val result = profileDataState) {
        is Resource.Success -> {
            profileData = result.data.data
            viewModel.saveLocalProfile(profileData)
            Log.d("Result Success", "SettingsContent: ${result.data}")
        }

        is Resource.Error -> {
            Log.d("Result Error", "SettingsContent: ${result.error}")

        }

        else -> {}
    }

    SettingsContent(
        localProfileData = localProfile,
        isKaryawan = localProfile.userRole == "Karyawan",
        isAdmin = localProfile.userRole == "Administrator",
        navigateToAuth = { navigateToAuth() },
        navigateToManageAccount = { navigateToManageAccount(it) },
        navigateToExport = { navigateToExport(it) },
        modifier = modifier
    )
}

@Composable
fun SettingsContent(
    navigateToAuth: () -> Unit,
    navigateToManageAccount: (title: String) -> Unit,
    navigateToExport: (title: String) -> Unit,
    localProfileData: ProfileData,
    isKaryawan: Boolean,
    isAdmin: Boolean,
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
        ProfilCard(
            title = localProfileData.userFullName,
            subtitle = localProfileData.departmentName,
            modifier = Modifier.padding(vertical = 36.dp)
        )
        if (!isKaryawan) {
            if (isAdmin) {
                SettingsMenu(
                    icon = painterResource(R.drawable.manage_accounts_24px),
                    text = "Kelola Pengguna",
                    onClick = { navigateToManageAccount("Kelola Pengguna") })
            }
            SettingsMenu(
                icon = painterResource(id = R.drawable.open_in_new_24px),
                text = "Ekspor Laporan",
                onClick = { navigateToExport("Ekspor Laporan") })
        }
        SettingsMenu(
            icon = painterResource(id = R.drawable.close_24px),
            text = "Sign Out",
            onClick = {
                viewModel.logout()
                navigateToAuth()
            })

    }
}



