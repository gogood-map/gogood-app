package com.gogood.mobile.home.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Sell
import androidx.compose.material.icons.filled.Work
import androidx.compose.material.icons.outlined.Work
import androidx.compose.material.icons.sharp.Favorite
import androidx.compose.material.icons.sharp.Home
import androidx.compose.material.icons.sharp.LocationOn
import androidx.compose.material.icons.sharp.School
import androidx.compose.material.icons.sharp.Work
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gogood.mobile.menu.domain.models.Endereco
import com.gogood.mobile.menu.domain.models.EnderecoResponse
import com.gogood.mobile.menu.domain.models.TipoEndereco
import com.gogood.mobile.ui.theme.GoGoodTheme
import com.gogood.mobile.ui.theme.GogoodBorderWhite
import com.gogood.mobile.ui.theme.GogoodWhite

@Composable
fun ItemBarraEnderecoFavorito(modifier: Modifier = Modifier, enderecoResponse: EnderecoResponse) {
    Row(modifier = modifier
        .background(color = Color.White, shape = RoundedCornerShape(8.dp))
        .border(1.dp, color = GogoodBorderWhite, shape = RoundedCornerShape(8.dp) ),

    ) {
        Row (
            modifier = modifier
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ){
            when (enderecoResponse.tipoEndereco) {
                TipoEndereco.TRABALHO.tipo -> {
                    Icon(imageVector = Icons.Sharp.Work, contentDescription = "Ícone trabalho")
                }

                TipoEndereco.OUTRO.tipo -> {
                    Icon(imageVector = Icons.Sharp.LocationOn, contentDescription = "Ícone outro")
                }

                TipoEndereco.FACULDADE.tipo -> {
                    Icon(imageVector = Icons.Sharp.School, contentDescription = "Ícone Faculdade")
                }

                TipoEndereco.PARCEIRO_A.tipo -> {
                    Icon(imageVector = Icons.Sharp.Favorite, contentDescription = "Ícone Parceiro(a)")
                }

                TipoEndereco.CASA.tipo -> {
                    Icon(imageVector = Icons.Sharp.Home, contentDescription = "Ícone Faculdade")
                }
            }

            Text(text = "${enderecoResponse.enderecos.rua}, ${enderecoResponse.enderecos.numero}")

        }
    }
}

@Preview
@Composable
private fun ItemBarraEnderecoFavoritoPreview() {
    GoGoodTheme {
        ItemBarraEnderecoFavorito(
            enderecoResponse = EnderecoResponse(
                tipoEndereco = TipoEndereco.TRABALHO.tipo,
                enderecos = Endereco(
                    id=1,
                    cep="08473-010",
                    rua= "Rua Cachoeira das Garças",
                    numero = "123",
                    cidade = "a",
                     bairro = "",
                    createdAt = ""
                )
            )
        )
    }
}