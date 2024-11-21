package com.gogood.mobile.menu.apresentation.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.PlaylistAdd
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gogood.mobile.menu.apresentation.viewmodels.MenuViewModel
import com.gogood.mobile.menu.domain.models.EnderecoResponse
import com.gogood.mobile.ui.theme.GogoodBorderWhite
import com.gogood.mobile.ui.theme.GogoodGray
import com.gogood.mobile.ui.theme.GogoodHeartFavoriteRed
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun Favoritos() {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Titulo(texto = "Favoritos")
        ListaFavoritos()
    }
}

@Composable
fun ListaFavoritos(menuViewModel: MenuViewModel = koinViewModel()) {


    val (showDialog, setShowDialog) = remember { mutableStateOf(false) }


    Column {
        LazyColumn(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .height(320.dp)
        ) {
            items(menuViewModel.enderecosFavoritos) { fav ->
                ItemListaFavorito(fav)
                HorizontalDivider(color = GogoodBorderWhite)
            }

        }
        Row(
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
                .padding(horizontal = 16.dp)
                .clickable {
                    setShowDialog(true)
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

@Composable
fun ItemListaFavorito(favorito: EnderecoResponse) {
    Row(
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)
            .padding(top = 10.dp)
    ) {
        Icon(
            Icons.Filled.Favorite, contentDescription = "Botão de fechar menu",
            modifier = Modifier
                .size(28.dp),
            tint = GogoodHeartFavoriteRed
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier.fillMaxHeight()
        ) {
            TextoItemListaFavorito(texto = "${favorito.enderecos.rua}, ${favorito.enderecos.numero}")
            TextoSubItemListaFavorito(texto = favorito.tipoEndereco)
        }
    }
}

@Composable
fun TextoItemListaFavorito(texto: String) {
    Text(
        text = texto,
        style = TextStyle(
            fontSize = 20.sp,
            fontWeight = FontWeight.Normal,
            color = GogoodGray,
        )
    )
}

@Composable
fun TextoSubItemListaFavorito(texto: String) {
    Text(
        text = texto,
        style = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            color = GogoodGray,
        )
    )
}

