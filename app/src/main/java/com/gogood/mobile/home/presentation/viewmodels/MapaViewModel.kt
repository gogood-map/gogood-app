package com.gogood.mobile.home.presentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gogood.mobile.home.data.repository.IMapRepository
import com.gogood.mobile.utils.LocalizacaoObserver
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.heatmaps.WeightedLatLng
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.android.ext.koin.androidContext

class MapaViewModel (private val mapRepository: IMapRepository,
    private val localizacaoObserver: LocalizacaoObserver)
    : ViewModel() {
    var isLoading by mutableStateOf(true)
    var showMenu by mutableStateOf(false)


    private val _localizacao = MutableStateFlow(LatLng(-23.557984712431196, -46.661776750487476))
    val localizacao: StateFlow<LatLng> = _localizacao

    var posicaoCamera by mutableStateOf(
        CameraPosition.
            builder()
                .target(_localizacao.value)
                .zoom(16f)
                .build()
    )
    private set


    var coordenadasOcorrenciasMapaDeCalor = MutableStateFlow<List<WeightedLatLng>>(emptyList())

    init {

        localizacaoObserver.observeLocation().onEach { novaLocalizacao->
            _localizacao.value = novaLocalizacao

            posicaoCamera = CameraPosition.
            builder()
                .target(_localizacao.value)
                .zoom(16f)
                .build()
        }.launchIn(viewModelScope)
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

    fun atualizarPosicaoCamera(latLng: LatLng){
        posicaoCamera =  CameraPosition.
        builder()
            .target(latLng)
            .zoom(16f)
            .build()
    }


    private fun raioBusca():Double{

        val zoom = posicaoCamera.zoom
        var radius = 5.0
        if (zoom <= 13) {
            return radius
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