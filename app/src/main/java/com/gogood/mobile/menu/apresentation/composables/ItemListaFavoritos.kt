package com.gogood.mobile.menu.apresentation.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gogood.mobile.menu.domain.models.EnderecoResponse
import com.gogood.mobile.ui.theme.GogoodGray
import com.gogood.mobile.ui.theme.GogoodHeartFavoriteRed

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