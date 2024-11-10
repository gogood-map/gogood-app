package com.gogood.mobile.menu.apresentation.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.gogood.mobile.address.presentation.composables.AddressForm
import com.gogood.mobile.auth.apresentation.composables.SolicitacaoEntrada
import com.gogood.mobile.menu.apresentation.viewmodels.MenuViewModel
import com.gogood.mobile.ui.theme.GogoodGray
import com.gogood.mobile.ui.theme.GogoodGraySubTitle
import org.koin.compose.viewmodel.koinViewModel
import java.time.LocalDateTime

@Composable
fun Menu(
    navController: NavController,
    modifier: Modifier = Modifier,
    onClose: () -> Unit,
    menuViewModel: MenuViewModel = koinViewModel()
) {
    val mensagem = gerarBoasVindas()
    var showAddressForm by remember { mutableStateOf(false) }

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
            SolicitacaoEntrada(navController = navController)

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
                Favoritos(openAddressForm = {
                    showAddressForm = true
                })
            }
            item{
                Servicos(navController)
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

@Composable
fun CardBoasVindas(nomeUsuario: String, mensagem: String) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column {
            Titulo(texto = "Olá, $nomeUsuario")
            SubTitulo(texto = mensagem)
        }
        ImagemUsuario(url = "https://static.thenounproject.com/png/897141-200.png")
    }
}

@Composable
fun Titulo(texto: String) {
    Text(
        text = texto,
        style = TextStyle(
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = GogoodGray,
        )
    )
}

@Composable
fun SubTitulo(texto: String) {
    Text(
        text = texto,
        style = TextStyle(
            fontSize = 20.sp,
            fontWeight = FontWeight.Normal,
            color = GogoodGraySubTitle,
        )
    )
}

@Composable
fun ImagemUsuario(url: String) {
    AsyncImage(
        model = url,
        contentDescription = "Foto do Usuário",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .clip(CircleShape)
            .size(80.dp)
    )
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