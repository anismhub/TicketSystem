package com.anismhub.ticketsystem.presentation.components

import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.anismhub.ticketsystem.R

@Composable
fun CreateFAB(onClick: () -> Unit) {
    FloatingActionButton(onClick = onClick) {
        Icon(painter = painterResource(id = R.drawable.add_24px), "Create Ticket")
    }
}