package com.gogood.mobile.home.presentation.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.gogood.mobile.ui.theme.GogoodGray
import com.gogood.mobile.ui.theme.GogoodWhite

@Composable
fun BotaoMeioTransporte(
    icone: ImageVector,
    ativo: Boolean = true,
    selecionado: Boolean = false,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick, shape = RoundedCornerShape(30), colors = ButtonDefaults.buttonColors(
            containerColor = if (selecionado) GogoodGray else Color(0xFFCFCFCF),
            contentColor = if (selecionado) Color.White else Color(0xFF7C7C7C),
        ),
        modifier = Modifier.padding(8.dp)
    ) {
        Icon(icone, contentDescription = "Botão de opção de pedestre na rota", tint = GogoodWhite)
    }
}