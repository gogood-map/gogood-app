package com.gogood.mobile.home.presentation.composables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gogood.mobile.home.presentation.viewmodels.MapaViewModel
import com.gogood.mobile.ui.theme.GogoodGreen
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun PesquisaRotas() {
    val mapaViewModel = koinViewModel<MapaViewModel>()
    val rotas by mapaViewModel.rotas.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        MeiosTransporte()
        Spacer(modifier = Modifier.height(16.dp))
        if(rotas.isNotEmpty()){
            ListaOpcoesRotas()
            Spacer(modifier = Modifier.height(16.dp))
        }
        Button(
            onClick = {
                mapaViewModel.buscarRota()
            },
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

