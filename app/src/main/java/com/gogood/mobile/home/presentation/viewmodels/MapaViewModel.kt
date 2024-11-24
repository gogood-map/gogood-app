package com.gogood.mobile.home.presentation.viewmodels

import android.annotation.SuppressLint
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.gogood.mobile.home.data.repository.IMapRepository
import com.gogood.mobile.home.domain.models.RelatorioOcorrenciasResponse
import com.gogood.mobile.home.domain.models.RotaResponse
import com.gogood.mobile.home.domain.usecases.IObterCoordenadasOcorrenciaRaioUseCase
import com.gogood.mobile.home.presentation.stateholders.MainStateHolder
import com.gogood.mobile.ui.theme.GogoodOrange
import com.gogood.mobile.ui.theme.GogoodPolylines
import com.gogood.mobile.utils.ConexaoInternetObserver
import com.gogood.mobile.utils.LocalizacaoObserver
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
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import okhttp3.internal.notify
import okhttp3.internal.notifyAll

class MapaViewModel (private val mapRepository: IMapRepository,
        private val localizacaoObserver: LocalizacaoObserver,
        private val conexaoObserver: ConexaoInternetObserver,
        private val obterCoordenadasOcorrenciasRaioUseCase: IObterCoordenadasOcorrenciaRaioUseCase
) : ViewModel() {

    var uiState = MutableLiveData<MainStateHolder>(MainStateHolder.Loading)



    private val _rotas = MutableStateFlow<List<RotaResponse>>(emptyList())
    val rotas: StateFlow<List<RotaResponse>> = _rotas

    var rotaEscolhida = MutableLiveData<RotaResponse>()
    var coordenadasTrajetoRota = MutableLiveData<List<LatLng>>()


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

    private var _localizacao = MutableStateFlow(LatLng(-23.550395929666593, -46.63396345499372))
    val localizacao: StateFlow<LatLng> = _localizacao


    var abaBandeja by mutableIntStateOf(0)

    var relatorioOcorrenciasResponse = MutableLiveData<RelatorioOcorrenciasResponse>()

    var posicaoCameraBusca by mutableStateOf(
        CameraPosition.
            builder()
                .target(_localizacao.value)
                .zoom(16f)
                .build()
    )
    private set

    private val _conectado = MutableStateFlow(false)
    var conectado: StateFlow<Boolean> = _conectado



    init {
        viewModelScope.launch {
            conexaoObserver.observarConexao.collect{
                _conectado.value = it
                conectado = _conectado
            }
        }
    }



    @SuppressLint("MissingPermission")
    fun observarUsuario(){
        if(localizacaoObserver.permissaoLocalizacao.value){
            localizacaoObserver.observerLocalizacao().onEach { novaLocalizacao->
                _localizacao.value = novaLocalizacao
                if(!localizouUsuario){
                    atualizarPosicaoCamera(localizacao.value)
                    localizouUsuario = true
                }
            }.launchIn(viewModelScope)
        }
    }





    fun buscarOcorrenciasRaio(){

        val lat = posicaoCameraBusca.target.latitude
        val lng = posicaoCameraBusca.target.longitude
        val raio = definirRaioBusca()

        viewModelScope.launch {
            val resposta = obterCoordenadasOcorrenciasRaioUseCase(LatLng(lat, lng), raio)
            if(resposta.isSuccess){
                uiState.value = MainStateHolder.Content(resposta.getOrNull()!!)
                atualizarMapaCalor()
            }
        }
    }
    fun buscarRelatorioRaio(){

        val lat = posicaoCameraBusca.target.latitude
        val lng = posicaoCameraBusca.target.longitude
        val raio = definirRaioBusca()

        viewModelScope.launch {
            delay(1500)
            val resposta = mapRepository.buscarRelatorioRaio(lat, lng, raio)
            if(resposta.isSuccessful){
                resposta.body()?.let {
                   relatorioOcorrenciasResponse.value = it
                }

            }
        }
    }
    fun atualizarPosicaoCamera(latLng: LatLng, angulo: Float, zoom: Float = 18f){
        posicaoCameraBusca =CameraPosition.builder()
            .target(latLng)
            .tilt(
               angulo
            )
            .zoom(zoom)
            .build()
    }
    fun atualizarPosicaoCamera(latLng: LatLng, zoom: Float = 18f){
        posicaoCameraBusca =CameraPosition.builder()
            .target(latLng)
            .zoom(zoom)
            .build()
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

                atualizarPosicaoCamera(latLng)
            }
        }
   }

    private fun atualizarMapaCalor() {
        mapaCalorCamada?.remove()
        val content = uiState.value as MainStateHolder.Content

        if(content.coordenadasOcorrenciasMapaDeCalor.isNotEmpty()){
            val cores = intArrayOf(
                Color.Yellow.toArgb(),
                GogoodOrange.toArgb(),
                Color.Red.toArgb(),
            )
            val pontosIntensidade = floatArrayOf(0.1f,  0.3f, 1f)
            val heatmapTileProvider = HeatmapTileProvider.Builder()
                .gradient(Gradient(cores, pontosIntensidade))
                .maxIntensity(7.0)
                .data(content.coordenadasOcorrenciasMapaDeCalor)
                .build()

            var opcoes =  TileOverlayOptions().tileProvider(heatmapTileProvider)

            mapaCalorCamada = mapa?.addTileOverlay(opcoes)
        }

    }
    fun limparRotas(){
        _rotas.value = emptyList()
    }
    fun limparPolylinesRotas(){
        polylines.forEach {
            it.remove()
        }
    }
    fun buscarRotas(){
        viewModelScope.launch {
            val requisicao = mapRepository.buscarRota(meioRota.value,
                entradaOrigemRota.value,
                entradaDestinoRota.value)
            if(requisicao.isSuccessful){
                _rotas.value = requisicao.body()!!
                exibirPolylinesOpcoes()
            }

        }
    }
    fun definirRotaEscolhida(rota: RotaResponse, corPolyline: Color){
        showBottomSheet = false
        limparPolylinesRotas()
        rotaEscolhida.value = rota
        coordenadasTrajetoRota.value = PolyUtil.decode(rotaEscolhida.value!!.polyline)
        exibirPolyline(rotaEscolhida.value!!, corPolyline)
        atualizarPosicaoCamera(
            latLng = coordenadasTrajetoRota.value!![0],
            angulo = 30f
        )

    }
    fun exibirPolyline(rotaResponse: RotaResponse, corPolyline: Color){
        val polylineDecodificada = PolyUtil.decode(rotaResponse.polyline)

        val polylineOptions = PolylineOptions().apply {
            width(10f)
            color(corPolyline.toArgb())
            addAll(polylineDecodificada)
        }
        val polyline = mapa?.addPolyline(polylineOptions)
        polylines.add(polyline!!)
    }

    fun exibirPolylinesOpcoes() {
        polylines.forEach {
            it.remove()
        }
        _rotas.value.sortedBy {
            it.qtdOcorrenciasTotais
        }.forEachIndexed { i, rota ->
            exibirPolyline(rota, GogoodPolylines[i])


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
