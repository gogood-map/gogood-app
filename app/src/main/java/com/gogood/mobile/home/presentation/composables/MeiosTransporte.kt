package com.gogood.mobile.home.presentation.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.DirectionsBike
import androidx.compose.material.icons.automirrored.filled.DirectionsWalk
import androidx.compose.material.icons.filled.DirectionsBus
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.gogood.mobile.home.domain.models.MeioTransporteEnum
import com.gogood.mobile.home.presentation.viewmodels.MapaViewModel
import com.gogood.mobile.ui.theme.GogoodGray
import com.gogood.mobile.ui.theme.GogoodWhite
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MeiosTransporte() {
    val mapaViewModel = koinViewModel<MapaViewModel>()
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
        BotaoMeioTransporte(icone = Icons.AutoMirrored.Filled.DirectionsWalk, selecionado = foiAPe) {
            meioSelecionado = "A pé"
            mapaViewModel.meioRota.value = MeioTransporteEnum.A_PE.meio
        }
        BotaoMeioTransporte(icone = Icons.AutoMirrored.Filled.DirectionsBike, selecionado = foiBike) {
            meioSelecionado = "Bike"
            mapaViewModel.meioRota.value = MeioTransporteEnum.BIKE.meio
        }
        BotaoMeioTransporte(icone = Icons.Filled.DirectionsCar, selecionado = foiCarro) {
            meioSelecionado = "Carro"
            mapaViewModel.meioRota.value = MeioTransporteEnum.VEICULO.meio
        }
        BotaoMeioTransporte(icone = Icons.Filled.DirectionsBus, selecionado = foiTransportePublico) {
            meioSelecionado = "Transporte Público"
            mapaViewModel.meioRota.value = MeioTransporteEnum.TRANSPORTE_PUBLICO.meio
        }
    }
}

