package com.anismhub.ticketsystem.presentation.screen.tickets.detailticket

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import com.anismhub.ticketsystem.BuildConfig
import net.engawapg.lib.zoomable.rememberZoomState
import net.engawapg.lib.zoomable.zoomable

@Composable
fun ImageCommentScreen(
    commentImage: String,
    modifier: Modifier = Modifier
) {
    val image = "${BuildConfig.BASE_URL}public/uploads/$commentImage"
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxSize()
    ) {
        AsyncImage(
            model = image,
            contentDescription = "Image Comment",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxSize()
                .zoomable(rememberZoomState())
        )
    }
}