package com.gogood.mobile.home.presentation.composables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gogood.mobile.home.presentation.viewmodels.MapaViewModel
import com.gogood.mobile.ui.theme.GogoodBorderWhite
import com.gogood.mobile.ui.theme.GogoodGray
import com.gogood.mobile.ui.theme.GogoodGreen
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AbaRotas() {
    val mapaViewModel = koinViewModel<MapaViewModel>()
    val rotas by mapaViewModel.rotas.collectAsState()
    val entradasRotasValidas = mapaViewModel.entradaOrigemRota.value.isNotBlank() ||
            mapaViewModel.entradaOrigemRota.value.isNotBlank()
    var corBotao by remember {
        mutableStateOf(GogoodGreen)
    }
    if(entradasRotasValidas){
        corBotao = GogoodGreen
    }else{
        corBotao = GogoodBorderWhite
    }
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 16.dp, start = 16.dp)
    ) {
            item {
                MeiosTransporte()
            }
            item {
                if(rotas.isNotEmpty()){
                    Spacer(modifier = Modifier.height(16.dp))
                    ListaOpcoesRotas()
                }
            }
            item {
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                        if (entradasRotasValidas) {
                            mapaViewModel.buscarRotas()
                        }
                    },
                    shape = RoundedCornerShape(30),
                    modifier = Modifier.fillMaxWidth(), colors = ButtonDefaults.buttonColors(
                        containerColor = corBotao
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
    }


