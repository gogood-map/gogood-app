package com.gogood.mobile.home.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gogood.mobile.home.domain.models.CrimeQtd

@Composable
fun ItemListaCardRelatorio(modifier: Modifier = Modifier, cor: Color, crime: CrimeQtd) {
    Row(horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()){
        Box(modifier = modifier.background(cor, RoundedCornerShape(25)).size(8.dp))
        Text(text = crime.crime, fontSize = 14.sp)
        Text(text = "-", fontSize = 14.sp)
        Text(text = "${crime.qtdOcorrido} ocorrências", fontSize = 14.sp)

    }
}