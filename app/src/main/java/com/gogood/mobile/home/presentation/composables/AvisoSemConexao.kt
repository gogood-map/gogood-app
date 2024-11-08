package com.gogood.mobile.home.presentation.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SignalWifiConnectedNoInternet4
import androidx.compose.material.icons.filled.WifiOff
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gogood.mobile.ui.theme.GoGoodTheme
import com.gogood.mobile.ui.theme.GogoodGray

@Composable
fun AvisoSemConexao(modifier: Modifier = Modifier) {
    Column (modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center){
        Icon(imageVector = Icons.Default.WifiOff,
            contentDescription = "Sem Conexão", modifier = modifier.size(120.dp),
            tint = GogoodGray)

        Text(text = "Oops! Sem internet", fontSize = 24.sp, fontWeight = FontWeight.Bold,
            color = GogoodGray)
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "Por favor, verifique sua conexão para continuar navegando.", textAlign = TextAlign.Center,
            color = GogoodGray)
    }
}

@Preview(showBackground = true)
@Composable
private fun AvisoSemConexaoPreview() {
    GoGoodTheme {
        AvisoSemConexao()
    }
}