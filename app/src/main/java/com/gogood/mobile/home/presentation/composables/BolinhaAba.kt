package com.gogood.mobile.home.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.gogood.mobile.ui.theme.GogoodGray
import com.gogood.mobile.ui.theme.GogoodGreen
import com.gogood.mobile.ui.theme.GogoodOptionRed

@Composable
fun BolinhaAba(ativa: Boolean){
    var corBolinha by remember{
        mutableStateOf(GogoodGray)
    }
    if(ativa){
        corBolinha = GogoodGreen
    }else{
        corBolinha = GogoodGray
    }


    Box(modifier = Modifier
        .clip(CircleShape)
        .background(
            corBolinha,
            CircleShape
        )
        .size(8.dp))
}