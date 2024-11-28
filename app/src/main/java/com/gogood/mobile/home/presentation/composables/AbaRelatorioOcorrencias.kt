package com.gogood.mobile.home.presentation.composables

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.gogood.mobile.home.presentation.viewmodels.MapaViewModel
import com.gogood.mobile.ui.theme.GogoodGreen
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AbaRelatorioOcorrencias(modifier: Modifier = Modifier){
    val brush = Brush.horizontalGradient(listOf(GogoodGreen, Color(0xFF0B9970)))
    val mapaViewModel = koinViewModel<MapaViewModel>()
    val relatorio = mapaViewModel.relatorioOcorrenciasResponse.value


    LazyColumn (
        modifier = modifier
            .fillMaxSize()
            .padding(end = 16.dp, start = 16.dp)
    ){
        item {
            if(relatorio != null){
                val crimeMaiorQtd =  relatorio?.top5Ocorrencias?.sortedByDescending {
                    it.qtdOcorrido
                }?.get(0)

                CardRelatorio(
                    titulo="${relatorio.qtdOcorrencias}",
                    subTitulo = "Total de Ocorrências", corFundo = brush)
                Spacer(modifier = Modifier.height(16.dp))
                CardRelatorio(
                    titulo="${crimeMaiorQtd?.crime?.replace(" - OUTROS", "")}",
                    subTitulo = "Crime + Ocorrências")
                Spacer(modifier = Modifier.height(16.dp))
                CardRelatorioLista()
            }else{

            }

        }
    }
}

//${mapaViewModel.relatorioOcorrenciasResponse.value?.qtdOcorrencias}