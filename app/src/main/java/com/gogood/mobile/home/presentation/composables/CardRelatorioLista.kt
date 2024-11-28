package com.gogood.mobile.home.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gogood.mobile.home.domain.models.CrimeQtd
import com.gogood.mobile.home.presentation.viewmodels.MapaViewModel
import com.gogood.mobile.ui.theme.GogoodBorderWhite
import com.gogood.mobile.ui.theme.GogoodGray
import com.gogood.mobile.ui.theme.GogoodOptionRed
import com.gogood.mobile.ui.theme.GogoodOptionYellow
import com.gogood.mobile.ui.theme.GogoodOrange
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun CardRelatorioLista(modifier: Modifier = Modifier,
                       corFundo: Color= GogoodBorderWhite,
                       ) {
    val mapaViewModel = koinViewModel<MapaViewModel>()
    val relatorio = mapaViewModel.relatorioOcorrenciasResponse.value
    if(relatorio != null){
        var listaCrimes = relatorio.top5Ocorrencias

        if(listaCrimes.size > 3){
            listaCrimes=  listaCrimes.dropLast(listaCrimes.size-3)
        }
        listaCrimes = listaCrimes.sortedBy {
            it.qtdOcorrido
        }

        LazyColumn(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier= modifier
                .background(corFundo, RoundedCornerShape(16))
                .height(108.dp)
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            val cores = listOf(
                GogoodOptionRed,
                GogoodOrange,
                GogoodOptionYellow
            )

            itemsIndexed(listaCrimes){i, item ->
                ItemListaCardRelatorio(cor = cores[i], crime = item)
            }
        }
    }else{
        Column (
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier= modifier
                .background(corFundo, RoundedCornerShape(16))
                .height(108.dp)
                .padding(16.dp)
                .fillMaxWidth()
        ){
            Text(text = "Sem informações para essa área nesse momento. Tente novamente mais tarde.",
                fontWeight = FontWeight.SemiBold)
        }
    }
    

    
}