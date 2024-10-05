package com.example.gogood.ui.componentes.bandeja

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsBike
import androidx.compose.material.icons.filled.DirectionsBus
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.DirectionsWalk
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@Composable
fun MeiosTransporte() {
    var meioSelecionado by remember { mutableStateOf("") }
    var foiTransportePublico by remember { mutableStateOf(false) }
    var foiCarro by remember { mutableStateOf(false) }
    var foiBike by remember { mutableStateOf(false) }
    var foiAPe by remember { mutableStateOf(false) }

    when (meioSelecionado) {
        "A pé" -> {
            foiTransportePublico = false
            foiCarro = false
            foiBike = false
            foiAPe = true
        }
        "Bike" -> {
            foiTransportePublico = false
            foiCarro = false
            foiBike = true
            foiAPe = false
        }
        "Carro" -> {
            foiTransportePublico = false
            foiCarro = true
            foiBike = false
            foiAPe = false
        }
        "Transporte Público" -> {
            foiTransportePublico = true
            foiCarro = false
            foiBike = false
            foiAPe = false
        }
    }

    Row(modifier = Modifier.fillMaxWidth(), Arrangement.SpaceAround) {
        BotaoMeioTransporte(icone = Icons.Filled.DirectionsWalk, selecionado = foiAPe) {
            meioSelecionado = "A pé"
        }
        BotaoMeioTransporte(icone = Icons.Filled.DirectionsBike, selecionado = foiBike) {
            meioSelecionado = "Bike"
        }
        BotaoMeioTransporte(icone = Icons.Filled.DirectionsCar, selecionado = foiCarro) {
            meioSelecionado = "Carro"
        }
        BotaoMeioTransporte(icone = Icons.Filled.DirectionsBus, selecionado = foiTransportePublico) {
            meioSelecionado = "Transporte Público"
        }
    }
}