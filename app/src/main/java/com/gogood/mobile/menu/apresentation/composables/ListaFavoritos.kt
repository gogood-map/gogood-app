package com.gogood.mobile.menu.apresentation.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.PlaylistAdd
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gogood.mobile.menu.apresentation.viewmodels.MenuViewModel
import com.gogood.mobile.ui.theme.GogoodBorderWhite
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ListaFavoritos(
    menuViewModel: MenuViewModel = koinViewModel(),
    openAddressForm: () -> Unit
) {


    val (showDialog, setShowDialog) = remember { mutableStateOf(false) }


    Column {
        LazyColumn(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .height(280.dp)
        ) {
            items(menuViewModel.enderecosFavoritos) { fav ->
                ItemListaFavorito(fav)
                HorizontalDivider(color = GogoodBorderWhite)
            }

        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
                .padding(horizontal = 16.dp)
                .clickable {
                    openAddressForm()
                }
        ) {
            Icon(
                Icons.AutoMirrored.Filled.PlaylistAdd,
                modifier = Modifier
                    .size(28.dp),
                contentDescription = "Botão de Adicionar Favorito"
            )
            TextoItemListaFavorito("Adicionar Favorito")
        }
    }

    if (showDialog) {
        ModalFavorito(onDismiss = { setShowDialog(false) })
    }

}
