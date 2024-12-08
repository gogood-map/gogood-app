package com.gogood.mobile.menu.apresentation.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.gogood.mobile.ui.theme.GogoodGray

@Composable
fun CardServico(texto: String, corConteudo: Color = Color.Black, corFundo: Color, icone: ImageVector, tamanho: Dp, ativo: Boolean = false,
                onClick: () -> Unit) {
    Card(
        onClick = onClick,
        colors = CardDefaults.cardColors(containerColor = if(ativo) corFundo else GogoodGray),
        modifier = Modifier.size(tamanho)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Icon(imageVector = icone, contentDescription = null, tint = if(ativo) corConteudo else Color.White)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = texto, color = if(ativo) corConteudo else Color.White)
        }
    }
}