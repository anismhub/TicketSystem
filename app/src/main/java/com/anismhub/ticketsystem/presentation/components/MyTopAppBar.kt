package com.anismhub.ticketsystem.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.anismhub.ticketsystem.R
import com.anismhub.ticketsystem.presentation.theme.MyTypography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar(
    title: String,
    navController: NavHostController,
    showTopBar: Boolean,
    modifier: Modifier = Modifier
) {
    if (showTopBar) {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = title,
                    style = MyTypography.headlineMedium.copy(fontWeight = FontWeight.Bold)
                )
            },
            modifier = modifier
                .padding(horizontal = 8.dp),
            navigationIcon = {
                IconButton(
                    onClick = { navController.navigateUp() },
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.round_arrow_back24),
                        contentDescription = "Kembali"
                    )
                }
            }
        )
    }

}