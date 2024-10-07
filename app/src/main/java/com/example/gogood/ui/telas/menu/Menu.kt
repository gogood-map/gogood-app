package com.example.gogood.ui.telas.menu

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.gogood.ui.theme.GogoodGray
import com.example.gogood.ui.theme.GogoodGraySubTitle
import java.time.LocalDateTime

@Composable
fun Menu(
    modifier: Modifier = Modifier,
    onClose: () -> Unit,
) {
    val mensagem = gerarBoasVindas()

    Column(
        verticalArrangement = Arrangement.spacedBy(26.dp),
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 24.dp)
            .padding(top = 16.dp)
    ) {
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

        CardBoasVindas("Teresa", mensagem)
        Favoritos()
        Servicos()
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
        ImagemUsuario(url = "https://okawalivros.com.br/wp-content/uploads/2017/10/uma-pessoa-cativante-1080x675.jpg")
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