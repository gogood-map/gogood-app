package com.gogood.mobile.bandeja.presentation.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun Bandeja(
    abrir: Boolean,
    navController: NavController,
    modifier: Modifier = Modifier,
    opcoesRota: List<OpcaoRota>
) {
    if (!abrir) {
        return
    }

    Column(modifier = modifier) {
        PesquisaRotas(opcoesRota)
        Analise()
    }
}

data class OpcaoRota(val duracao: String, val qtdOcorrencias: Int, val distancia:Double)
