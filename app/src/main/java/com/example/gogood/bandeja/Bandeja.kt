package com.example.gogood.bandeja

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsBike
import androidx.compose.material.icons.filled.DirectionsBus
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.DirectionsWalk
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gogood.ui.theme.GoGoodTheme
import com.example.gogood.ui.theme.GogoodGray
import com.example.gogood.ui.theme.GogoodGreen
import com.example.gogood.ui.theme.GogoodWhite


@Composable
fun Bandeja(abrir:Boolean){
    

    val opcoes = listOf(
        OpcaoRota(duracao = "23 min", qtdOcorrencias = 300, distancia = 10.0),
        OpcaoRota(duracao = "13 min", qtdOcorrencias = 300, distancia = 5.0),
        OpcaoRota(duracao = "8 min", qtdOcorrencias = 300, distancia = 3.0),

        )
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        MeiosTransporte()

        ListaOpcoesRotas(opcoesRota = opcoes)
        Button(onClick = { /*TODO*/ },
            shape = RoundedCornerShape(30),
            modifier = Modifier.fillMaxWidth(),colors=ButtonDefaults.buttonColors(
            containerColor = GogoodGreen
        )) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = Icons.Filled.Search,
                    contentDescription = "Botão de Pesquisar Rota")
                Text(text = "Buscar rota")
            }
        }
        TituloBandeja("Análise de ocorrências")

    }
}
@Composable
fun TituloBandeja(texto:String){
    Text(text = texto, fontSize = 16.sp, color = GogoodGray)
}

@Composable
fun MeiosTransporte(){
    var meioSelecionado by remember {
        mutableStateOf("")
    }
    var foiTransportePublico by remember {
        mutableStateOf(false)
    }
    var foiCarro by remember {
        mutableStateOf(false)
    }
    var foiBike by remember {
        mutableStateOf(false)
    }
    var foiAPe by remember {
        mutableStateOf(false)
    }

    when(meioSelecionado){
        "A pé"->{
            foiTransportePublico = false
            foiCarro = false
            foiBike = false
            foiAPe = true
        }

        "Bike"->{
            foiTransportePublico = false
            foiCarro = false
            foiBike = true
            foiAPe = false
        }
        "Carro"->{
            foiTransportePublico = false
            foiCarro = true
            foiBike = false
            foiAPe = false
        }
        "Transporte Público"->{
            foiTransportePublico = true
            foiCarro = false
            foiBike = false
            foiAPe = false
        }

    }
    Row(modifier = Modifier.fillMaxWidth(), Arrangement.SpaceAround){
        BotaoMeioTransporte(icone = Icons.Filled.DirectionsWalk, selecionado = foiAPe){
            meioSelecionado = "A pé"
        }
        BotaoMeioTransporte(icone = Icons.Filled.DirectionsBike, selecionado = foiBike){
            meioSelecionado = "Bike"
        }
        BotaoMeioTransporte(icone = Icons.Filled.DirectionsCar, selecionado = foiCarro){
            meioSelecionado = "Carro"
        }
        BotaoMeioTransporte(icone = Icons.Filled.DirectionsBus, selecionado = foiTransportePublico){
            meioSelecionado = "Transporte Público"
        }

    }
}
@Composable
fun BotaoMeioTransporte(icone: ImageVector, ativo:Boolean = true, selecionado:Boolean=false,onClick: ()->Unit){

    Button(onClick = onClick, shape = RoundedCornerShape(30), colors = ButtonDefaults.buttonColors(
        containerColor =  if (selecionado) GogoodGray else Color(0xFFCFCFCF),
        contentColor = if (selecionado) Color.White else Color(0xFF7C7C7C),
    )) {

        Icon(icone, contentDescription = "Botão de opção de pedestre na rota", tint= GogoodWhite)
    }

}

@Composable
fun ListaOpcoesRotas(opcoesRota:List<OpcaoRota>){
    Column (verticalArrangement = Arrangement.spacedBy(10.dp)){
        TituloBandeja("Selecione uma opção")
        for (opcaoRota in opcoesRota) {
            BotaoOpcaoRota(opcao = opcaoRota)
        }
    }
}
@Composable
fun BotaoOpcaoRota(opcao:OpcaoRota, selecionado: Boolean = false){
    val razaoQtdOcorrenciasDistancia = opcao.qtdOcorrencias/opcao.distancia
    val cor = when{
        razaoQtdOcorrenciasDistancia <= 50 ->{
            Color.Green
        }
        razaoQtdOcorrenciasDistancia <= 75 ->{
            Color.Yellow
        }
        else -> {
            Color.Red
        }
    }
    val riscoTexto = when{
        razaoQtdOcorrenciasDistancia <= 50 ->{
            "Risco baixo"
        }
        razaoQtdOcorrenciasDistancia <= 75 ->{
            "Risco médio"
        }
        else -> {
           "Risco alto"
        }
    }


    Button(onClick = { /*TODO*/ },
        shape = RoundedCornerShape(20), elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 3.dp
        ),
        colors = ButtonDefaults.buttonColors(
            containerColor = if(selecionado) GogoodGray else Color.White,
            contentColor = if(!selecionado) GogoodGray else Color.White,
        ),
        contentPadding = PaddingValues(10.dp), modifier = Modifier.height(50.dp)) {
        Row (modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween){
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                Box(modifier = Modifier
                    .width(50.dp)
                    .height(15.dp)
                    .clip(RoundedCornerShape(30))
                    .background(cor))
                Text(text = riscoTexto)

            }

            Text(text = opcao.duracao)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun RotaOpcaoPreview(){
    GoGoodTheme {
       BotaoOpcaoRota(OpcaoRota(duracao = "3 min", qtdOcorrencias = 30, distancia = 3.0))
    }
}

@Preview
@Preview(showBackground = true)
@Composable
fun ListaOpcoesRotasPreview(){
    GoGoodTheme {
        val opcoes = listOf(
            OpcaoRota(duracao = "23 min", qtdOcorrencias = 300, distancia = 10.0),
            OpcaoRota(duracao = "13 min", qtdOcorrencias = 300, distancia = 5.0),
            OpcaoRota(duracao = "8 min", qtdOcorrencias = 300, distancia = 3.0),

            )
        ListaOpcoesRotas(opcoes)
    }
}

@Composable
@Preview(showBackground = true)
fun OpcaoTrajetoPreview(){
    GoGoodTheme {
       BotaoMeioTransporte(icone = Icons.Filled.DirectionsWalk){}
    }
}

@Composable
@Preview(showBackground = true)
fun MeiosTransportePreview(){
    GoGoodTheme {
        MeiosTransporte()
    }
}


@Composable
@Preview(showSystemUi = false, showBackground = true)
fun BandejaPreview(){
    GoGoodTheme {
        Bandeja(abrir = true)
    }
}