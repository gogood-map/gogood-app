package com.gogood.mobile.menu.apresentation.composables

import AddressForm
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.gogood.mobile.auth.apresentation.composables.SolicitacaoEntrada
import com.gogood.mobile.auth.apresentation.viewmodels.LoginViewModel
import com.gogood.mobile.menu.apresentation.viewmodels.MenuViewModel
import com.gogood.mobile.ui.theme.GogoodOptionRed
import org.koin.compose.viewmodel.koinViewModel
import java.time.LocalDateTime


@Composable
fun Menu(
    navController: NavController,
    modifier: Modifier = Modifier,
    onClose: () -> Unit,
) {
    val loginViewModel = koinViewModel<LoginViewModel>()
    val menuViewModel: MenuViewModel = koinViewModel()
    menuViewModel.closeMenu.value = onClose

    val mensagem = gerarBoasVindas()
    var showAddressForm by remember { mutableStateOf(false) }
    val pagerState = rememberPagerState (initialPage = 0){
        2
    }
    LaunchedEffect(menuViewModel.abaMenu) {
        pagerState.scrollToPage(menuViewModel.abaMenu)
    }
    LaunchedEffect(pagerState.currentPage) {
        menuViewModel.abaMenu = pagerState.currentPage
    }
    
    if(menuViewModel.usuario == null){
        Column(
            verticalArrangement = Arrangement.spacedBy(26.dp),
            modifier = modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(horizontal = 24.dp)
                .padding(top = 16.dp)
        ) {
            Column(modifier = Modifier.fillMaxWidth()){
                IconButton(
                    onClick = { onClose() },
                    modifier = Modifier
                        .size(32.dp)
                        .background(Color.Transparent)
                        .align(Alignment.End)
                ) {
                    Icon(
                        Icons.Sharp.Close,
                        contentDescription = "Fechar Menu",
                        modifier = Modifier
                            .size(30.dp)
                    )
                }
            }
            SolicitacaoEntrada()

        }
    }else{
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(26.dp),
            modifier = modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(horizontal = 24.dp)
                .padding(top = 16.dp)
        ) {
            item{
                Column(modifier = Modifier.fillMaxWidth()){
                    IconButton(
                        onClick = { onClose() },
                        modifier = Modifier
                            .size(32.dp)
                            .background(Color.Transparent)
                            .align(Alignment.End)
                    ) {
                        Icon(
                            Icons.Sharp.Close,
                            contentDescription = "Fechar Menu",
                            modifier = Modifier
                                .size(30.dp)
                        )
                    }
                }

            }

            item{
                CardBoasVindas(menuViewModel.usuario!!.nome!!, mensagem)
            }
            
            item{
                HorizontalPager(state = pagerState, modifier=Modifier.height(400.dp)) {atual->
                    when(atual){
                        0->{
                            Favoritos(openAddressForm = {
                                showAddressForm = true
                            })
                        }
                        1->{
                            Historico()
                        }
                    }
                }

            }
            item{
                Servicos(navController)
            }
            item {
                TextButton(onClick = {
                    loginViewModel.sair()
                    navController.navigate("Mapa")
                }) {
                    Text(
                        text = "Sair",
                        textDecoration = TextDecoration.Underline,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 16.sp,
                        color = GogoodOptionRed
                    )
                }

            }

        }
        AnimatedVisibility(
            visible = showAddressForm,
            enter = slideInHorizontally(initialOffsetX = { -it }),
            exit = slideOutHorizontally(targetOffsetX = { -it })
        ) {
            AddressForm(
                onSubmit = {
                    showAddressForm = false
                }
            )
        }
    }
}









private fun gerarBoasVindas(): String {
    val dataApoio: LocalDateTime = LocalDateTime.now()
    val mensagemBoasVindas = when {
        dataApoio.hour in 6..11 && dataApoio.minute <= 59 -> {
            "Bom dia! ☀️"
        }

        dataApoio.hour in 12..18 && dataApoio.minute <= 59 -> {
            "Boa tarde! 🌤️"
        }

        else -> {
            "Boa noite! 🌙"
        }
    }
    return mensagemBoasVindas
}