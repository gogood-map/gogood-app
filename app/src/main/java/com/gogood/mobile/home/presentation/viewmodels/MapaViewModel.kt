package com.gogood.mobile.home.presentation.viewmodels

import android.graphics.Color
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gogood.mobile.home.data.repository.IMapRepository
import com.gogood.mobile.home.domain.models.MeioTransporteEnum
import com.gogood.mobile.home.domain.models.RotaResponse
import com.gogood.mobile.ui.theme.GogoodGreen
import com.gogood.mobile.ui.theme.GogoodOptionRed
import com.gogood.mobile.ui.theme.GogoodOptionYellow
import com.gogood.mobile.ui.theme.coresPolyline
import com.gogood.mobile.utils.ConexaoInternetObserver
import com.gogood.mobile.utils.LocalizacaoObserver
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.Polyline
import com.google.android.gms.maps.model.PolylineOptions
import com.google.android.gms.maps.model.TileOverlay
import com.google.android.gms.maps.model.TileOverlayOptions
import com.google.maps.android.PolyUtil
import com.google.maps.android.heatmaps.Gradient
import com.google.maps.android.heatmaps.HeatmapTileProvider
import com.google.maps.android.heatmaps.WeightedLatLng
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class MapaViewModel (private val mapRepository: IMapRepository,
        private val localizacaoObserver: LocalizacaoObserver,
        private val conexaoObserver: ConexaoInternetObserver) : ViewModel() {

    var coordenadasOcorrenciasMapaDeCalor = MutableStateFlow<List<WeightedLatLng>>(emptyList())

    private val _rotas = MutableStateFlow<List<RotaResponse>>(emptyList())
    val rotas: StateFlow<List<RotaResponse>> = _rotas

    private val _conectado = MutableStateFlow(false)
    val contectado: StateFlow<Boolean> = _conectado

    var isLoading by mutableStateOf(true)
    var showMenu by mutableStateOf(false)
    var isSearchAddress by mutableStateOf(true)
    var isSearchRoute by mutableStateOf(false)
    private var localizouUsuario by mutableStateOf(false)


    var mapa: GoogleMap? by mutableStateOf(null)
    var mapaCalorCamada: TileOverlay? by mutableStateOf(null)


    var entradaBuscaEndereco = mutableStateOf("")
    var entradaOrigemRota = mutableStateOf("")
    var entradaDestinoRota = mutableStateOf("")
    var meioRota = mutableStateOf("")

    var markerBusca by mutableStateOf<Marker?>(null)
    var polylines = mutableStateListOf<Polyline>()

    var showBottomSheet by  mutableStateOf(false)

    private var _localizacao = MutableStateFlow(LatLng(-23.557984712431196, -46.661776750487476))
    val localizacao: StateFlow<LatLng> = _localizacao

    var posicaoCameraBusca by mutableStateOf(
        CameraPosition.
            builder()
                .target(_localizacao.value)
                .zoom(16f)
                .build()
    )
    private set




    init {
        viewModelScope.launch {

            conexaoObserver.isConnected.collect{
                _conectado.value = it
                isLoading = false
            }
        }



    }

    fun observarUsuario(){
        if(localizacaoObserver.permissaoLocalizacao.value){
            localizacaoObserver.observeLocation().onEach { novaLocalizacao->
                _localizacao.value = novaLocalizacao
                if(!localizouUsuario){
                    moverCamera(localizacao.value)
                    localizouUsuario = true
                }


            }.launchIn(viewModelScope)
        }
    }
    fun moverCamera(latLng: LatLng){
        mapa?.moveCamera(CameraUpdateFactory.newCameraPosition(
            CameraPosition.builder().target(latLng).zoom(16f).build()
        ))
    }

    fun alterarAnguloCamera(){
        val inclinacao = mapa?.cameraPosition?.tilt
        if (inclinacao != null) {
            mapa?.moveCamera(CameraUpdateFactory.newCameraPosition(
                CameraPosition.builder()
                    .target(_localizacao.value)
                    .tilt(
                        if (inclinacao > 0) 0f else 90f
                    )
                    .zoom(19f)
                    .build()
            ))
        }
    }

    fun buscarOcorrenciasRaio(){

        val lat = posicaoCameraBusca.target.latitude
        val lng = posicaoCameraBusca.target.longitude
        val raio = definirRaioBusca()

        viewModelScope.launch {
            delay(1500)
            val resposta = mapRepository.obterOcorrenciasRaio(lat, lng, raio)

            if(resposta.isSuccessful){

                resposta.body()?.let {
                    coordenadasOcorrenciasMapaDeCalor.value = it.coordenadasOcorrencias.map {
                        WeightedLatLng(
                            LatLng(it[1], it[0]),
                            1.0
                        )
                    }
                    if(coordenadasOcorrenciasMapaDeCalor.value.isNotEmpty()){
                       atualizarMapaCalor()
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

    private fun atualizarMapaCalor() {
        mapaCalorCamada?.remove()

        val cores = intArrayOf(
            Color.YELLOW,
            Color.rgb(255, 165, 0),
            Color.RED,
        )
        val pontosIntensidade = floatArrayOf(0.1f,  0.3f, 1f)
        val heatmapTileProvider = HeatmapTileProvider.Builder()
            .weightedData(coordenadasOcorrenciasMapaDeCalor.value)
            .gradient(Gradient(cores, pontosIntensidade))
            .maxIntensity(7.0)

            .build()

        var opcoes =  TileOverlayOptions().tileProvider(heatmapTileProvider)

        mapaCalorCamada = mapa?.addTileOverlay(opcoes)
    }


    fun buscarRota(){

        viewModelScope.launch {
            val requisicao = mapRepository.buscarRota(meioRota.value,
                entradaOrigemRota.value,
                entradaDestinoRota.value)
            if(requisicao.isSuccessful){
                _rotas.value = requisicao.body()!!

                alterarAnguloCamera()
                exibirPolylines()
            }

        }

    }

    private fun exibirPolylines() {
        polylines.forEach {
            it.remove()
        }


        _rotas.value.sortedBy {
            it.qtdOcorrenciasTotais
        }.forEachIndexed { i, rota ->
            val polylineDecodificada = PolyUtil.decode(rota.polyline)

            val polylineOptions = PolylineOptions().apply {
                width(10f)
                color(coresPolyline[i].toArgb())
                addAll(polylineDecodificada)
            }
            val polyline = mapa?.addPolyline(polylineOptions)

            polylines.add(polyline!!)


        }
    }

        private fun definirRaioBusca(): Double {

            val zoom = posicaoCameraBusca.zoom
            var radius = 5.0
            if (zoom <= 13) {
                return radius
            } else if (zoom <= 15) {
                radius = 2.5
            } else if (zoom <= 17) {
                radius = 1.25
            } else {
                radius = 0.575
            }
            return radius
        }
}
