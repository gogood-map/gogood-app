package com.gogood.mobile.home.presentation.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gogood.mobile.menu.apresentation.viewmodels.MenuViewModel
import com.gogood.mobile.menu.domain.models.EnderecoResponse
import com.gogood.mobile.ui.theme.GoGoodTheme
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun BarraEnderecosFavoritos(modifier: Modifier = Modifier, onClickItem: (String)->Unit) {
    val menuViewModel = koinViewModel<MenuViewModel>()

    LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        items(menuViewModel.enderecosFavoritos){
            ItemBarraEnderecoFavorito(modifier = Modifier.clickable {
                onClickItem(it.enderecos.rua+", "+it.enderecos.numero)
            },enderecoResponse = it)
        }
    }

}

@Preview
@Composable
private fun BarraEnderecosFavoritosPreview() {
    GoGoodTheme {
        BarraEnderecosFavoritos(){

        }
    }
}