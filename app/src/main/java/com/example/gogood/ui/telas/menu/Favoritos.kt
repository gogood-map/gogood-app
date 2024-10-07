package com.example.gogood.ui.telas.menu

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
import com.example.gogood.ui.theme.GogoodBorderWhite
import com.example.gogood.ui.theme.GogoodGray
import com.example.gogood.ui.theme.GogoodHeartFavoriteRed

@Composable
fun Favoritos() {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Titulo(texto = "Favoritos")
        ListaFavoritos()
    }
}

@Composable
fun ListaFavoritos() {
    val (showDialog, setShowDialog) = remember { mutableStateOf(false) }

    val favoritos = gerarFavoritos()

    Column {
        LazyColumn(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .height(320.dp)
        ) {
            items(favoritos) { fav ->
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
fun ItemListaFavorito(favorito: Favorito) {
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
            TextoItemListaFavorito(texto = "${favorito.logradouro}, ${favorito.numero}")
            TextoSubItemListaFavorito(texto = favorito.tipo)
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

fun gerarFavoritos(): List<Favorito> {
    val logradouros = listOf(
        "Avenida Paulista", "Rua Oscar Freire", "Rua das Flores", "Avenida Brasil",
        "Rua Augusta", "Rua Consolação", "Avenida Ipiranga", "Rua Haddock Lobo",
        "Rua Faria Lima", "Avenida Rebouças", "Rua Alameda Santos", "Rua Vergueiro",
        "Rua Teodoro Sampaio", "Avenida Pacaembu", "Rua Itapeva", "Rua Pamplona",
        "Rua Bela Cintra", "Rua dos Pinheiros", "Rua Bandeira Paulista", "Avenida Morumbi"
    )

    val tipos = listOf("Casa", "Escritório", "Parceiro(a)")

    val favoritos = mutableListOf<Favorito>()

    for (i in 1..5) {
        val logradouro = logradouros.random()
        val numero = (1..1000).random()
        val tipo = tipos.random()
        favoritos.add(Favorito(logradouro, numero, tipo))
    }

    return favoritos
}