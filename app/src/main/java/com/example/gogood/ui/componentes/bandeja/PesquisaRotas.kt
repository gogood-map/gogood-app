package com.example.gogood.ui.componentes.bandeja

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.gogood.ui.theme.GogoodGreen

@Composable
fun PesquisaRotas(opcoesRota: List<OpcaoRota>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp), verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        MeiosTransporte()
        ListaOpcoesRotas(opcoesRota = opcoesRota)
        Button(
            onClick = { /*TODO*/ },
            shape = RoundedCornerShape(30),
            modifier = Modifier.fillMaxWidth(), colors = ButtonDefaults.buttonColors(
                containerColor = GogoodGreen
            )
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Botão de Pesquisar Rota"
                )
                Text(text = "Buscar rota")
            }
        }
    }
}