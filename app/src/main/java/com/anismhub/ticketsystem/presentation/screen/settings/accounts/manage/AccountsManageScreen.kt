package com.anismhub.ticketsystem.presentation.screen.settings.accounts.manage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.PersonRemove
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.anismhub.ticketsystem.data.DataDummy
import com.anismhub.ticketsystem.presentation.components.MySearchBar
import com.anismhub.ticketsystem.presentation.components.ProfilCard

@Composable
fun AccountManageScreen() {

}

@Composable
fun AccountManageContent(
    navigateToCreateAccount: () -> Unit,
    navigateToUpdateAccount: () -> Unit,
    modifier: Modifier = Modifier
) {
    var query by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .padding(top = 16.dp)
            .padding(horizontal = 12.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            MySearchBar(
                query = query,
                onQueryChange = { query = it },
                modifier = Modifier
                    .weight(0.5f)
            )
            Spacer(modifier = Modifier.weight(0.2f))
            Button(onClick = {
                navigateToCreateAccount()
            }, shape = RoundedCornerShape(20), modifier = Modifier.weight(0.3f)) {
                Text(text = "Buat Pengguna", textAlign = TextAlign.Center)
            }
        }
        LazyColumn(
            contentPadding = PaddingValues(top = 4.dp, bottom = 24.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier.padding(top = 8.dp)
        ) {
            items(DataDummy.dummyTickets, key = { it.title }) {
                ProfilCard(title = it.title, subtitle = it.date,
                    icon = {
                        Icon(
                            imageVector = Icons.Rounded.PersonRemove,
                            contentDescription = "Hapus Pengguna"
                        )
                    },
                    onClickIcon = { },
                    onClick = {
                        navigateToUpdateAccount()
                    }
                )
            }
        }

    }
}