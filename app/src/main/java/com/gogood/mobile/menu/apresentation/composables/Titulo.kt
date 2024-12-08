package com.gogood.mobile.menu.apresentation.composables

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.gogood.mobile.ui.theme.GogoodGray

@Composable
fun Titulo(texto: String) {
    Text(
        text = texto,
        style = TextStyle(
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = GogoodGray,
        )
    )
}