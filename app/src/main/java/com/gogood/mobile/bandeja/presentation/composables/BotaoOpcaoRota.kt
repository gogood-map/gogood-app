package com.gogood.mobile.bandeja.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gogood.mobile.ui.theme.GogoodGray
import com.gogood.mobile.ui.theme.GogoodOptionGreen
import com.gogood.mobile.ui.theme.GogoodOptionRed
import com.gogood.mobile.ui.theme.GogoodOptionYellow

@Composable
fun BotaoOpcaoRota(opcao: OpcaoRota, selecionado: Boolean = false, onClick: () -> Unit) {
    val razaoQtdOcorrenciasDistancia = opcao.qtdOcorrencias / opcao.distancia
    val cor = when {
        razaoQtdOcorrenciasDistancia <= 50 -> GogoodOptionGreen
        razaoQtdOcorrenciasDistancia <= 75 -> GogoodOptionYellow
        else -> GogoodOptionRed
    }
    val riscoTexto = when {
        razaoQtdOcorrenciasDistancia <= 50 -> "Risco baixo"
        razaoQtdOcorrenciasDistancia <= 75 -> "Risco médio"
        else -> "Risco alto"
    }

    Button(
        onClick = onClick,
        shape = RoundedCornerShape(20),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 3.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (selecionado) GogoodGray else Color.White,
            contentColor = if (!selecionado) GogoodGray else Color.White,
        ),
        contentPadding = PaddingValues(10.dp),
        modifier = Modifier.height(50.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Box(
                    modifier = Modifier
                        .width(32.dp)
                        .height(12.dp)
                        .clip(RoundedCornerShape(30))
                        .background(cor)
                        .border(1.5.dp, if (selecionado) Color.White else Color.Transparent)
                )
                Text(text = riscoTexto, fontSize = 16.sp)
            }
            Text(text = "${opcao.distancia} km", fontSize = 16.sp)
        }
    }
}
