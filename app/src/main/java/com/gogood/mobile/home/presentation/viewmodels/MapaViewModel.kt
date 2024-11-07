package com.gogood.mobile.home.presentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gogood.mobile.home.data.repository.IMapRepository
import com.gogood.mobile.utils.LocalizacaoObserver
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.heatmaps.WeightedLatLng
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class MapaViewModel (private val mapRepository: IMapRepository,
    private val localizacaoObserver: LocalizacaoObserver)
    : ViewModel() {
    var isLoading by mutableStateOf(true)
    var showMenu by mutableStateOf(false)
    var isSearchAddress by mutableStateOf(true)
    var isSearchRoute by mutableStateOf(false)
    var localizouUsuario by mutableStateOf(false)
    var mapa: GoogleMap? by mutableStateOf(null)

    var entradaBuscaEndereco = mutableStateOf("")
    var entradaOrigemRota = mutableStateOf("")
    var entradaDestinoRota = mutableStateOf("")

    var markerBusca by mutableStateOf<Marker?>(null)

    private var _localizacao = MutableStateFlow(LatLng(-23.557984712431196, -46.661776750487476))
    val localizacao: StateFlow<LatLng> = _localizacao

    var posicaoCameraBusca by mutableStateOf(
        CameraPosition.
            builder()
                .target(_localizacao.value)
                .zoom(16f)
                .build()
    )

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
            if(!localizouUsuario){
                moverCamera(localizacao.value)
                localizouUsuario = true
            }


        }.launchIn(viewModelScope)
      //  buscarOcorrenciasRaio()

    }

    fun moverCamera(latLng: LatLng){
        mapa?.moveCamera( CameraUpdateFactory.newCameraPosition(
            CameraPosition.builder().target(latLng).zoom(16f).build()
        ))
    }

    fun buscarOcorrenciasRaio(){


        val lat = posicaoCameraBusca.target.latitude
        val lng = posicaoCameraBusca.target.longitude
        val raio = definirRaioBusca()

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
        posicaoCameraBusca = novaPosicao
    }

   fun buscarEndereco(entrada: String){
        viewModelScope.launch {
            val requisicao = mapRepository.buscarEndereco(entrada)

            if(requisicao.isSuccessful && requisicao.body() != null){
                markerBusca?.remove()

                markerBusca = mapa?.addMarker(
                    MarkerOptions()
                    .visible(false)
                    .title("Busca")
                    .position(LatLng(-23.557984712431196, -46.661776750487476))
                )

                val lat = requisicao.body()!!.candidates[0].geometry.location.lat
                val lng = requisicao.body()!!.candidates[0].geometry.location.lng
                val latLng = LatLng(lat, lng)

                markerBusca?.title = "Busca"
                markerBusca?.isVisible = true
                markerBusca?.position = latLng

                moverCamera(latLng)
            }
        }
   }


    private fun definirRaioBusca():Double{

        val zoom = posicaoCameraBusca.zoom
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