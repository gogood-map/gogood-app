package com.gogood.mobile.common.bandeja

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gogood.mobile.ui.theme.GogoodGray
import com.gogood.mobile.ui.theme.GogoodGreen

@Composable
fun PesquisaRotas(opcoesRota: List<OpcaoRota>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp), verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        MeiosTransporte()
        ListaOpcoesRotas(opcoesRota = opcoesRota)
        Button(
            onClick = { /*TODO*/ },
            shape = RoundedCornerShape(30),
            modifier = Modifier.fillMaxWidth(), colors = ButtonDefaults.buttonColors(
                containerColor = GogoodGreen
            )
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Botão de Pesquisar Rota"
                )
                Text(text = "Buscar rota")
            }
        }
    }
}

@Composable
fun ListaOpcoesRotas(opcoesRota: List<OpcaoRota>) {
    val selecoes = remember {
        mutableStateListOf<Boolean>().apply {
            addAll(List(opcoesRota.size) { false })
        }
    }

    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        TituloBandeja("Selecione uma opção")
        opcoesRota.forEachIndexed { index, opcaoRota ->
            BotaoOpcaoRota(opcao = opcaoRota, selecionado = selecoes[index]) {
                selecoes.forEachIndexed { i, _ ->
                    selecoes[i] = index == i && !selecoes[i]
                }
            }
        }
    }
}

@Composable
fun TituloBandeja(texto: String) {
    Text(text = texto, fontSize = 16.sp, color = GogoodGray, fontWeight = FontWeight.Medium)
}