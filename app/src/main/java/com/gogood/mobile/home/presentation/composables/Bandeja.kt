package com.gogood.mobile.home.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.gogood.mobile.auth.apresentation.composables.SolicitacaoEntrada
import com.gogood.mobile.auth.apresentation.viewmodels.LoginViewModel
import com.gogood.mobile.home.presentation.viewmodels.MapaViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun Bandeja(
    modifier: Modifier = Modifier
) {
    val mapaViewModel = koinViewModel<MapaViewModel>()
    val loginViewModel = koinViewModel<LoginViewModel>()
    val pagerState = rememberPagerState (initialPage = 0){
        2
    }

    LaunchedEffect(mapaViewModel.abaBandeja) {
        pagerState.scrollToPage(mapaViewModel.abaBandeja)
    }
    LaunchedEffect(pagerState.currentPage) {
        mapaViewModel.abaBandeja = pagerState.currentPage
    }
    Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){
        HorizontalPager(state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.75F)
        ) {atual->
            when(atual){
                0->{
                    AbaRelatorioOcorrencias()
                }
                1->{
                    if(loginViewModel.isLoggedIn.value){
                        AbaRotas()
                    }else{
                        SolicitacaoEntrada()
                    }

                }
            }
            
        }
        TabRow(
            modifier = Modifier
                .fillMaxHeight(0.25F)
                .width(100.dp),
            selectedTabIndex = mapaViewModel.abaBandeja,
            indicator = {

            },
            divider = {

            }) {
            for(index in 0 until pagerState.pageCount){
                Tab(
                    modifier = Modifier.size(32.dp),
                    selected =
                        index == mapaViewModel.abaBandeja,

                    onClick = {
                        mapaViewModel.abaBandeja = index
                    }) {
                    BolinhaAba(ativa = mapaViewModel.abaBandeja == index)
                }
            }
        }
    }
}

data class OpcaoRota(val duracao: String, val qtdOcorrencias: Int, val distancia:Double)
