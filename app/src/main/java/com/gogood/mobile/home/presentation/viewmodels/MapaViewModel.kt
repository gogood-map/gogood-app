package com.gogood.mobile.home.presentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gogood.mobile.home.data.repository.IMapRepository
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.heatmaps.WeightedLatLng
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MapaViewModel (private val mapRepository: IMapRepository): ViewModel() {
    var isLoading by mutableStateOf(true)
    val marcoZeroCoordenada = LatLng(-23.557984712431196, -46.661776750487476)
    var posicaoCamera by mutableStateOf(
        CameraPosition.
            builder()
                .target(marcoZeroCoordenada)
                .zoom(16f)
                .build()
    )
    private set

    var showMenu by mutableStateOf(false)
    var coordenadasOcorrenciasMapaDeCalor = MutableStateFlow<List<WeightedLatLng>>(emptyList())

    init {
        buscarOcorrenciasRaio()
    }
    fun buscarOcorrenciasRaio(){


        val lat = posicaoCamera.target.latitude
        val lng = posicaoCamera.target.longitude
        val raio = raioBusca()

        viewModelScope.launch {
            val resposta = mapRepository.obterOcorrenciasRaio(lat, lng, raio)

            if(resposta.isSuccessful){

                resposta.body()?.let {
                    coordenadasOcorrenciasMapaDeCalor.value = it.coordenadasOcorrencias.map {
                        WeightedLatLng(
                            LatLng(it[1], it[0]),
                            0.1
                        )
                    }
                }

            }
        }

    }

    fun atualizarPosicaoCamera(novaPosicao: CameraPosition){
        posicaoCamera = novaPosicao
    }


    private fun raioBusca():Double{

        val zoom = posicaoCamera.zoom
        var radius = 5.0
        if (zoom <= 13) {
            return radius;
        }
        else if(zoom <= 15){
            radius = 2.5
        }else if(zoom <= 17){
            radius = 1.25
        }else{
            radius = 0.575
        }
        return radius
    }
}