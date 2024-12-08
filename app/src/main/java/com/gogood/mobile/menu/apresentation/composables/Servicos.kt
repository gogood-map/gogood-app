package com.gogood.mobile.menu.apresentation.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.sharp.ExitToApp
import androidx.compose.material.icons.sharp.Favorite
import androidx.compose.material.icons.sharp.History
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.gogood.mobile.auth.apresentation.composables.SolicitacaoEntrada
import com.gogood.mobile.auth.apresentation.viewmodels.LoginViewModel
import com.gogood.mobile.menu.apresentation.viewmodels.MenuViewModel
import com.gogood.mobile.ui.theme.GogoodOptionRed
import org.koin.compose.viewmodel.koinViewModel
import kotlin.math.log

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Servicos(navController: NavController) {
    val menuViewModel: MenuViewModel = koinViewModel()

    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {

        Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
            CardServico(texto = "Favoritos", corFundo = Color(0xFFFFCBCD), icone = Icons.Outlined.Favorite, tamanho = 100.dp,
                ativo = menuViewModel.abaMenu == 0) {
                menuViewModel.abaMenu = 0
            }
            Spacer(modifier = Modifier.width(24.dp))
            CardServico(texto = "Histórico", corFundo = Color(0xFFB0DCFC), icone = Icons.Sharp.History, tamanho = 100.dp,
                ativo = menuViewModel.abaMenu == 1) {
                menuViewModel.abaMenu = 1
            }
        }
    }
}

