package com.gogood.mobile.home.presentation.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import com.gogood.mobile.home.presentation.viewmodels.MapaViewModel
import com.gogood.mobile.ui.theme.GogoodPolylines
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun ListaOpcoesRotas() {
    val mapaViewModel = koinViewModel<MapaViewModel>()
    val rotas by mapaViewModel.rotas.collectAsState()

    val textos = listOf("Risco Baixo", "Risco Médio", "Risco Alto")
    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        TituloBandeja("Selecione uma opção:")
        rotas.sortedBy {
            it.qtdOcorrenciasTotais
        }.forEachIndexed { i, rota->
            val opcaoRota = OpcaoRota(
                qtdOcorrencias = rota.qtdOcorrenciasTotais,
                distancia = rota.distancia,
                duracao = rota.duracao
            )
            BotaoOpcaoRota(opcao = opcaoRota, GogoodPolylines[i], textos[i]) {
                mapaViewModel.iniciarRota(rota, GogoodPolylines[i])
            }
        }
    }
}
