package com.gogood.mobile.home.presentation.composables

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.gogood.mobile.ui.theme.GogoodGray

@Composable
fun TituloBandeja(texto: String) {
    Text(text = texto, fontSize = 16.sp, color = GogoodGray, fontWeight = FontWeight.Medium)
}