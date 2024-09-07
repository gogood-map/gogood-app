package com.example.gogood

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
import com.example.gogood.ui.theme.displayFontFamily
import java.time.LocalDateTime



@Composable
fun Menu() {
    val data = LocalDateTime.now()
    val mensagem = GerarBoasVindas(data)
    Column(
        verticalArrangement = Arrangement.spacedBy(26.dp),
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 16.dp)
            .padding(top = 24.dp)
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
            CardServico(texto = "Analytics", cor = Color(0xFFB0FCCD), icone = Icons.Sharp.Analytics, tamanho = 100.dp)
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
        fontFamily = displayFontFamily,
        style = TextStyle(
            fontFamily = displayFontFamily,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        ),
        fontWeight = FontWeight.Bold,
        color = Cinza,
    )
}

@Composable
fun SubTitulo(texto: String) {
    Text(
        text = texto,
        fontFamily = displayFontFamily,
        style = TextStyle(
            fontFamily = displayFontFamily,
            fontSize = 20.sp,
            fontWeight = FontWeight.Normal
        ),
        color = CinzaSubTitulo,
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
    val favoritos: MutableList<Favorito> = mutableListOf(
        Favorito("Rua Peixe Peixoto", 146, "Escritório"),
        Favorito("Rua das Madureiras", 466, "Casa"),
        Favorito("Rua Paulo Sampaio", 832, "Parceiro(a)"),
    )

    val (showDialog, setShowDialog) = remember { mutableStateOf(false) }

    Box {
        LazyColumn(modifier = Modifier.padding(horizontal = 16.dp)) {
            items(favoritos) { fav ->
                ItemListaFavorito(fav)
                HorizontalDivider(color = BordaItemLista)
            }
            item {
                Row(
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp)
                        .clickable {
                            setShowDialog(true) // Abre o modal ao clicar
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
        }
        // Exibe o modal se o estado showDialog for true
        if (showDialog) {
            ModalFavorito(onDismiss = { setShowDialog(false) })
        }
    }
}

@Composable
fun ModalFavorito(onDismiss: () -> Unit) {
    Dialog(onDismissRequest = onDismiss) {
        // Conteúdo do modal
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
                Text("Adicionar", fontSize = 16.sp, fontWeight = FontWeight.Bold) // Estilo do texto
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
            tint = Vermelho

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
        fontFamily = displayFontFamily,
        style = TextStyle(
            fontFamily = displayFontFamily,
            fontSize = 20.sp,
            fontWeight = FontWeight.Normal
        ),
        color = Cinza
    )
}

@Composable
fun TextoSubItemListaFavorito(texto: String) {
    Text(
        text = texto,
        fontFamily = displayFontFamily,
        style = TextStyle(
            fontFamily = displayFontFamily,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal
        ),
        color = CinzaSubTitulo,
    )
}
@Composable
fun TextoNomeServico(texto: String){
    Text(
        text = texto,
        fontFamily = displayFontFamily,
        style = TextStyle(
            fontFamily = displayFontFamily,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        ),
        color = CinzaSubTitulo
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
                Icon(icone, contentDescription = "Ícone", tint = ServicoNome)
                TextoNomeServico(texto)
            }


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


@Preview
@Composable
fun ItemFavoritoListaPreview() {
    ItemListaFavorito(Favorito("Rua Mickey", 1928, "Escritório"))

}


@Preview
@Composable
fun ListaFavoritosPreview() {
    GoGoodTheme {
        ListaFavoritos()
    }
}

@Preview
@Composable
fun CardServicoPreview() {
    CardServico(texto = "Card", cor = ServicoNome, tamanho = 80.dp,
        icone = Icons.Sharp.Close)
}