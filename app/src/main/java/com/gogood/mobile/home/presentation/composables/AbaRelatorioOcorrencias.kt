package com.gogood.mobile.home.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gogood.mobile.home.presentation.viewmodels.MapaViewModel
import com.gogood.mobile.ui.theme.GogoodBorderWhite
import com.gogood.mobile.ui.theme.GogoodGray
import com.gogood.mobile.ui.theme.GogoodGreen
import com.gogood.mobile.ui.theme.GogoodWhite
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AbaRelatorioOcorrencias(modifier: Modifier = Modifier){
    val brush = Brush.horizontalGradient(listOf(GogoodGreen, Color(0xFF0B9970)))
    val mapaViewModel = koinViewModel<MapaViewModel>()

    LazyColumn (
        modifier = modifier
            .fillMaxSize()
            .padding(end = 16.dp, start = 16.dp)

    ){
        item {
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                modifier= Modifier
                    .background(brush, RoundedCornerShape(16))
                    .height(108.dp)
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Text(text = "200",
                    fontSize = 28.sp,
                    color = GogoodGray,
                    fontWeight = FontWeight.SemiBold)
                Text(text = "Total de Ocorrências",
                    fontSize = 18.sp,
                    color = GogoodGray,
                    fontWeight = FontWeight.Medium)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                modifier= Modifier
                    .background(GogoodBorderWhite, RoundedCornerShape(16))
                    .height(108.dp)
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Text(text = "200",
                    fontSize = 28.sp,
                    color = GogoodGray,
                    fontWeight = FontWeight.SemiBold)
                Text(text = "Total de Ocorrências",
                    fontSize = 18.sp,
                    color = GogoodGray,
                    fontWeight = FontWeight.Medium)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                modifier= Modifier
                    .background(brush, RoundedCornerShape(16))
                    .height(108.dp)
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Text(text = "200",
                    fontSize = 28.sp,
                    color = GogoodGray,
                    fontWeight = FontWeight.SemiBold)
                Text(text = "Total de Ocorrências",
                    fontSize = 18.sp,
                    color = GogoodGray,
                    fontWeight = FontWeight.Medium)
            }
        }
    }
}

//${mapaViewModel.relatorioOcorrenciasResponse.value?.qtdOcorrencias}