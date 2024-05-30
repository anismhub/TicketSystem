package com.anismhub.ticketsystem.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.ui.graphics.vector.ImageVector

enum class TabItem(
    val title: String
) {
    Open(title = "Open"),
    OnProgress(title = "On Progress"),
    Closed(title = "Closed"),
}
