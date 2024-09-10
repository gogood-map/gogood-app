package com.example.gogood.menu

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.PlaylistAdd
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.sharp.Analytics
import androidx.compose.material.icons.sharp.Close
import androidx.compose.material.icons.sharp.History
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import coil.compose.AsyncImage
import com.example.gogood.ui.theme.CianoButton
import com.example.gogood.ui.theme.CinzaFont
import com.example.gogood.ui.theme.GoGoodTheme
import com.example.gogood.ui.theme.GogoodBorderWhite
import com.example.gogood.ui.theme.GogoodCardGray
import com.example.gogood.ui.theme.GogoodGray
import com.example.gogood.ui.theme.GogoodGraySubTitle
import com.example.gogood.ui.theme.GogoodGreen
import com.example.gogood.ui.theme.GogoodHeartFavoriteRed
import java.nio.file.WatchEvent
import java.time.LocalDateTime



@Composable
fun Menu(modifier: Modifier = Modifier) {
    val data = LocalDateTime.now()
    val mensagem = GerarBoasVindas(data)
    Column(
        verticalArrangement = Arrangement.spacedBy(26.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .padding(top = 16.dp)
    ) {
        Icon(
            Icons.Sharp.Close,
            contentDescription = "Botão de fechar menu",
            modifier = Modifier
                .align(Alignment.End)
                .size(30.dp)
        )
        CardBoasVindas("Teresa", mensagem)
        Favoritos()
        Servicos()
    }
}

@Composable
fun Favoritos() {
    Column (verticalArrangement = Arrangement.spacedBy(8.dp)){
        Titulo(texto = "Favoritos")
        ListaFavoritos()
    }
}

@Composable
fun Servicos() {
    Column (verticalArrangement = Arrangement.spacedBy(16.dp)){
        Titulo(texto = "Serviços")
        Row (horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()){
            CardServico(texto = "Histórico", cor = Color(0xFFB0DCFC), icone = Icons.Sharp.History, tamanho = 100.dp)
            Spacer(modifier= Modifier.width(24.dp))
            CardServico(texto = "Analaytics", cor = Color(0xFFB0FCCD), icone = Icons.Sharp.Analytics, tamanho = 100.dp)
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
        ImagemUsuario(url = "https://midias.correio24horas.com.br/2024/08/14/davi-brito-2386295.jpg")
    }
}

@Composable
fun Titulo(texto: String) {
    Text(
        text = texto,
        style = TextStyle(
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        ),
        fontWeight = FontWeight.Bold,
        color = GogoodGray,
    )
}

@Composable
fun SubTitulo(texto: String) {
    Text(
        text = texto,
        style = TextStyle(
            fontSize = 20.sp,
            fontWeight = FontWeight.Normal
        ),
        color = GogoodGraySubTitle,
    )
}

@Composable
fun ImagemUsuario(url: String) {
    AsyncImage(
        model = url,
        contentDescription = "Foto do usuário",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .clip(CircleShape)
            .width(80.dp)
            .height(80.dp)

    )
}

@Composable
fun ListaFavoritos() {
    val (showDialog, setShowDialog) = remember { mutableStateOf(false) }

    val favoritos = gerarFavoritos()

    Column {
        LazyColumn(modifier = Modifier
            .padding(horizontal = 16.dp)
            .height(260.dp)) {
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
fun ModalFavorito(onDismiss: () -> Unit) {
    Dialog(onDismissRequest = onDismiss) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(Color.White)
                .padding(24.dp)
        ) {
            Text(
                text = "Adicionar favorito",
                modifier = Modifier.padding(bottom = 16.dp),
                style = TextStyle(fontSize = 24.sp), fontWeight = FontWeight(500), color = CinzaFont
            )

            val (logradouro, setLogradouro) = remember { mutableStateOf("") }
            val (numero, setNumero) = remember { mutableStateOf("") }
            val (tipo, setTipo) = remember { mutableStateOf("") }


            TextField(
                value = logradouro,
                onValueChange = setLogradouro,
                label = { Text("Logradouro") },
                placeholder = { Text("Digite o logradouro") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                textStyle = TextStyle(fontSize = 18.sp)
            )

            BasicTextField(
                value = logradouro,
                onValueChange = setLogradouro,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                decorationBox = { innerTextField ->
                    if (logradouro.isEmpty()) {
                        Text("Logradouro")
                    }
                    innerTextField()
                }
            )

            BasicTextField(
                value = numero,
                onValueChange = setNumero,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                decorationBox = { innerTextField ->
                    if (numero.isEmpty()) {
                        Text("Número")
                    }
                    innerTextField()
                }
            )

            BasicTextField(
                value = tipo,
                onValueChange = setTipo,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                decorationBox = { innerTextField ->
                    if (tipo.isEmpty()) {
                        Text("Tipo (Ex: Casa, Trabalho)")
                    }
                    innerTextField()
                }
            )
            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {},
                colors = ButtonDefaults.buttonColors(
                    containerColor = CianoButton,
                    contentColor = Color.White
                ),

                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(51.88.dp)
            ) {
                Text("Adicionar", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }

        }
    }
}

@Composable
fun ItemListaFavorito(favorito: Favorito) {

    Row(
        verticalAlignment = Alignment.Top, horizontalArrangement = Arrangement.spacedBy(10.dp),
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
            fontWeight = FontWeight.Normal
        ),
        color = GogoodGray
    )
}

@Composable
fun TextoSubItemListaFavorito(texto: String) {
    Text(
        text = texto,
        style = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal
        ),
        color = GogoodGraySubTitle,
    )
}
@Composable
fun TextoNomeServico(texto: String){
    Text(
        text = texto,

        style = TextStyle(
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        ),
        color = GogoodGraySubTitle
    )
}
@Composable
fun CardServico(texto: String, cor: Color, icone: ImageVector, tamanho: Dp) {
    Card(
        modifier = Modifier
            .width(tamanho)
            .height(tamanho),
        colors = CardDefaults.cardColors(
            containerColor = cor
        ), shape = RoundedCornerShape(20)
    ) {
        Box(modifier = Modifier

            .fillMaxSize(),
            contentAlignment = Alignment.Center){
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(icone, contentDescription = "Ícone", tint = GogoodCardGray)
                TextoNomeServico(texto)
            }


        }




    }
}




@Preview(showBackground = true)
@Composable
fun ItemFavoritoListaPreview() {
    ItemListaFavorito(Favorito("Rua Mickey", 1928, "Escritório"))

}

@Preview(showBackground = true)
@Composable
fun ListaFavoritosPreview() {
    GoGoodTheme {
        ListaFavoritos()
    }
}
@Preview(showBackground = true)
@Composable
fun CardServicoPreview() {
    CardServico(texto = "Card", cor = GogoodGreen, tamanho = 80.dp,
        icone = Icons.Sharp.Close)
}
@Preview(showBackground = true)
@Composable
fun MenuPreview(){
    GoGoodTheme {
        Scaffold (modifier = Modifier.fillMaxSize()){innerPadding->
            Menu(modifier = Modifier.padding(innerPadding))
        }
    }
}

private fun GerarBoasVindas(data: LocalDateTime): String {
    var dataApoio: LocalDateTime = LocalDateTime.now()
    var mensagemBoasVindas = ""
    when {
        dataApoio.hour in 6..11 && dataApoio.minute <= 59 -> {
            mensagemBoasVindas = "Bom dia! ☀️"
        }

        dataApoio.hour in 12..18 && dataApoio.minute <= 59 -> {
            mensagemBoasVindas = "Boa tarde! 🌤️"
        }

        else -> {
            mensagemBoasVindas = "Boa noite! 🌙"
        }
    }
    return mensagemBoasVindas
}

fun gerarFavoritos(): List<Favorito> {
    val logradouros = listOf("Avenida Paulista", "Rua Oscar Freire", "Rua das Flores", "Avenida Brasil",
        "Rua Augusta", "Rua Consolação", "Avenida Ipiranga", "Rua Haddock Lobo",
        "Rua Faria Lima", "Avenida Rebouças", "Rua Alameda Santos", "Rua Vergueiro",
        "Rua Teodoro Sampaio", "Avenida Pacaembu", "Rua Itapeva", "Rua Pamplona",
        "Rua Bela Cintra", "Rua dos Pinheiros", "Rua Bandeira Paulista", "Avenida Morumbi")

    val tipos = listOf("Casa", "Escritório", "Parceiro(a)")

    val favoritos = mutableListOf<Favorito>()

    for (i in 1..100) {
        val logradouro = logradouros.random()
        val numero = (1..1000).random()
        val tipo = tipos.random()
        favoritos.add(Favorito(logradouro, numero, tipo))
    }

    return favoritos
}