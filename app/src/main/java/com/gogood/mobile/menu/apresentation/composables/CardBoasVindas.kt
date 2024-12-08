package com.gogood.mobile.menu.apresentation.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun CardBoasVindas(nomeUsuario: String, mensagem: String) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column {
            Titulo(texto = "Olá, $nomeUsuario")
            SubTitulo(texto = mensagem)
        }
        ImagemOnline(url = "https://api.dicebear.com/9.x/initials/png?seed=${nomeUsuario}?scale=50")
    }
}