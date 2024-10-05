package com.example.gogood.ui.componentes.bandeja

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.gogood.ui.theme.GogoodGray

@Composable
fun TituloBandeja(texto: String) {
    Text(text = texto, fontSize = 16.sp, color = GogoodGray, fontWeight = FontWeight.Medium)
}