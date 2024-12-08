package com.gogood.mobile.home.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import com.gogood.mobile.ui.theme.GogoodBorderWhite
import com.gogood.mobile.ui.theme.GogoodGray
import com.gogood.mobile.ui.theme.GogoodWhite
import com.gogood.mobile.utils.extensions.sentenceCase

@Composable
fun ItemListaCardRelatorio(modifier: Modifier = Modifier, cor: Color, crime: CrimeQtd) {
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()){
        Box(modifier = modifier.background(cor, RoundedCornerShape(25)).height(12.dp).width(24.dp))
        TextoItemCardRelatorio(crime.crime.replace(" - OUTROS", "").sentenceCase().trim())
        TextoItemCardRelatorio("-")
        TextoItemCardRelatorio(crime.qtdOcorrido.toString()+" ocorrências")

    }
}
@Composable
fun TextoItemCardRelatorio(texto: String) {
    Text(text = texto, fontSize = 16.sp,
        fontWeight = FontWeight.Medium,
        color = GogoodGray)
}