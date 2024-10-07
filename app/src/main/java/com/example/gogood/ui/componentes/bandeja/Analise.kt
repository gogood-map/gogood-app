package com.example.gogood.ui.componentes.bandeja

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Analise() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 34.dp).padding(top = 0.dp), verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        TituloBandeja("Análise de ocorrências")
    }
}