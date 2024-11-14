package com.gogood.mobile.home.presentation.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun Bandeja(
    modifier: Modifier = Modifier
) {


    Column(modifier = modifier) {
        PesquisaRotas()
    }
}

data class OpcaoRota(val duracao: String, val qtdOcorrencias: Int, val distancia:Double)
