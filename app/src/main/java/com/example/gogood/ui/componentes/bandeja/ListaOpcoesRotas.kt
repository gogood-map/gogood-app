package com.example.gogood.ui.componentes.bandeja

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

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