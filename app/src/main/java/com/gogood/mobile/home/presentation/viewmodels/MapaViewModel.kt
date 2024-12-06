package com.gogood.mobile.home.presentation.viewmodels

import android.annotation.SuppressLint
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gogood.mobile.home.data.repository.IMapRepository
import com.gogood.mobile.home.domain.models.RelatorioOcorrenciasResponse
import com.gogood.mobile.home.domain.models.RotaResponse
import com.gogood.mobile.home.domain.usecases.IBuscaEnderecoUseCase
import com.gogood.mobile.home.domain.usecases.IObterCoordenadasOcorrenciaRaioUseCase
import com.gogood.mobile.home.domain.usecases.IObterRelatorioRaioUseCase
import com.gogood.mobile.home.presentation.stateholders.MainStateHolder
import com.gogood.mobile.ui.theme.GogoodOrange
import com.gogood.mobile.ui.theme.GogoodPolylines
import com.gogood.mobile.utils.IConexaoUtils
import com.gogood.mobile.utils.ILocalizacaoUtils
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
import com.google.maps.android.ktx.awaitAnimateCamera
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class MapaViewModel (private val mapRepository: IMapRepository,
                     val localizacaoUtils: ILocalizacaoUtils,
                     private val conexaoUtils: IConexaoUtils,
                     private val obterCoordenadasOcorrenciasRaioUseCase: IObterCoordenadasOcorrenciaRaioUseCase,
                     private val obterRelatorioRaioUseCase: IObterRelatorioRaioUseCase,
                     private val buscaEnderecoUseCase: IBuscaEnderecoUseCase
) : ViewModel() {


    val _uiState: MutableLiveData<MainStateHolder> by lazy{
        MutableLiveData(MainStateHolder.Loading)
    }
    val uiState: MutableState<MainStateHolder> = mutableStateOf(MainStateHolder.Loading)

    var salvarEnderecoProcurado = mutableStateOf(true)

    private val _rotas = MutableStateFlow<List<RotaResponse>>(emptyList())
    val rotas: StateFlow<List<RotaResponse>> = _rotas

    val direcao = mutableStateOf(90f)
    val cameraMapaAcompanhaUsuario = mutableStateOf(false)
    val modoRota = mutableStateOf(false)
    var isSearchAddress by mutableStateOf(true)
    var isSearchRoute by mutableStateOf(false)
    var mapa: GoogleMap? by mutableStateOf(null)
    var entradaBuscaEndereco = mutableStateOf("")
    var entradaOrigemRota = mutableStateOf("")
    var entradaDestinoRota = mutableStateOf("")
    var meioRota = mutableStateOf("")
    var showBottomSheet by  mutableStateOf(false)
    var abaBandeja by mutableIntStateOf(0)
    var relatorioOcorrenciasResponse = MutableLiveData<RelatorioOcorrenciasResponse>()
    val enderecosRecentesPesquisados = MutableStateFlow<List<String>>(emptyList())


    private var markerBusca by mutableStateOf<Marker?>(null)
    private var polylines = mutableStateListOf<Polyline>()
    private var localizacao = MutableStateFlow(LatLng(-23.550395929666593, -46.63396345499372))
    private var mapaCalorCamada: TileOverlay? by mutableStateOf(null)
    private var localizouUsuario by mutableStateOf(false)
    private var rotaEscolhida = MutableLiveData<RotaResponse>()
    private var coordenadasTrajetoRota = MutableLiveData<List<LatLng>>()


    var posicaoCameraMapa by mutableStateOf(
        CameraPosition.
            builder()
                .target(localizacao.value)
                .zoom(16f)
                .build()
    )
    var ultimaPosicaoBuscaMapa = mutableStateOf(LatLng(
        posicaoCameraMapa.target.latitude, posicaoCameraMapa.target.longitude
    ))


    init {
        viewModelScope.launch() {
            conexaoUtils.observarConexao.collect{
                if(it){
                    atualizarUiState(MainStateHolder.Content())
                }else{
                    _uiState.value = MainStateHolder.NoConnection
                    uiState.value = _uiState.value!!
                }
            }
        }

    }

    @SuppressLint("MissingPermission")
    fun observarLocalizacaoUsuario(){
        if(localizacaoUtils.permissaoLocalizacao.value){
            localizacaoUtils.observerLocalizacao().onEach { novaLocalizacao->
               
                localizacao.value = LatLng(novaLocalizacao.latitude, novaLocalizacao.longitude)
                direcao.value = novaLocalizacao.bearing

                if(!localizouUsuario){
                    atualizarPosicaoCamera(localizacao.value)
                    buscarOcorrenciasRaio()
                    buscarRelatorioRaio()
                    localizouUsuario = true
                }

                if(cameraMapaAcompanhaUsuario.value){
                    atualizarPosicaoCamera(
                        angulo = if(modoRota.value) 90f else 0f,
                        latLng = localizacao.value,
                        zoom = 22f
                    )
                }


            }.launchIn(viewModelScope)
        }
    }


    fun atualizarUiState(novoEstado: MainStateHolder){
        _uiState.value = novoEstado
        uiState.value = _uiState.value!!
    }


    fun buscarOcorrenciasRaio(){

        val lat = posicaoCameraMapa.target.latitude
        val lng = posicaoCameraMapa.target.longitude
        val raio = definirRaioBusca()

        viewModelScope.launch {
            val resposta = obterCoordenadasOcorrenciasRaioUseCase(LatLng(lat, lng), raio)

            resposta.onSuccess {
                atualizarUiState(MainStateHolder.Content(resposta.getOrNull()!!))
                atualizarMapaCalor()

            }


            resposta.onFailure {
                atualizarUiState(MainStateHolder.Error("Mapa de Calor", it.message!!))
            }


        }
    }

    fun buscarRelatorioRaio(){

        val lat = posicaoCameraMapa.target.latitude
        val lng = posicaoCameraMapa.target.longitude

        val latLng = LatLng(lat, lng)
        val raio = definirRaioBusca()
        viewModelScope.launch {
            val resposta = obterRelatorioRaioUseCase(latLng, raio)
            resposta.onSuccess {
                relatorioOcorrenciasResponse.value = resposta.getOrNull()!!
            }
            resposta.onFailure {
                atualizarUiState(MainStateHolder.Error("Mapa de Calor", "Erro ao buscar ocorrências"))
            }

        }
    }

    //region Camera Mapa
    fun atualizarPosicaoCamera(latLng: LatLng, angulo: Float=0f, zoom: Float = 18f, animacao: Boolean = true){
        posicaoCameraMapa =CameraPosition.builder()
            .target(latLng)
            .tilt(
               angulo
            )
            .bearing(direcao.value)
            .zoom(zoom)
            .build()

        if(animacao){
            viewModelScope.launch {
                mapa?.awaitAnimateCamera(CameraUpdateFactory.newCameraPosition(posicaoCameraMapa),
                    durationMs = 1000)
            }
        }
        else{
            mapa?.moveCamera(CameraUpdateFactory.newCameraPosition(posicaoCameraMapa))
        }

        //
    }


    fun atualizarPosicaoCamera(novaPosicao: CameraPosition){
        posicaoCameraMapa = novaPosicao
        mapa?.moveCamera(CameraUpdateFactory.newCameraPosition(posicaoCameraMapa))
    }

    fun atualizarPosicaoCameraLocalizacaoUsuario(){
        atualizarPosicaoCamera(localizacao.value, zoom=20f)
    }
    // endregion

   fun buscarEndereco(entrada: String){
        viewModelScope.launch {
            val resultado = buscaEnderecoUseCase(entrada)

            if(salvarEnderecoProcurado.value ){
                mapRepository.salvarEnderecoPesquisado(entrada)
            }


            resultado.onSuccess {
                val enderecoCandidato = it.candidates[0]
                val latLng = LatLng(
                    enderecoCandidato.geometry.location.lat,
                    enderecoCandidato.geometry.location.lng
                )
                markerBusca?.remove()

                markerBusca = mapa?.addMarker(
                    MarkerOptions()
                        .visible(false)
                        .title("Busca")
                        .position(LatLng(-23.557984712431196, -46.661776750487476))
                )

                markerBusca?.title = entrada
                markerBusca?.isVisible = true
                markerBusca?.position = latLng

                atualizarPosicaoCamera(latLng)
            }
            resultado.onFailure {
                atualizarUiState(MainStateHolder.Error("Busca endereço", it.message!!))
            }

        }
   }

    fun atualizarMapaCalor() {

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


    // region rotas
    fun buscarRotas(){
        viewModelScope.launch {

            if(entradaOrigemRota.value.isEmpty()){
                entradaOrigemRota.value = "${localizacao.value.latitude}, ${localizacao.value.longitude}"
            }
            val requisicao = mapRepository.buscarRota(meioRota.value,
                entradaOrigemRota.value,
                entradaDestinoRota.value)
            if(requisicao.isSuccessful){
                _rotas.value = requisicao.body()!!
                exibirOpcoesRotas()
            }

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

    fun iniciarRota(rota: RotaResponse, corPolyline: Color){
        showBottomSheet = false
        limparPolylinesRotas()
        rotaEscolhida.value = rota
        coordenadasTrajetoRota.value = PolyUtil.decode(rotaEscolhida.value!!.polyline)
        exibirPolyline(rotaEscolhida.value!!, corPolyline)
        atualizarPosicaoCamera(
            latLng = coordenadasTrajetoRota.value!![0],
            angulo = 90f
        )
        cameraMapaAcompanhaUsuario.value = true
        modoRota.value = true
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


    fun exibirOpcoesRotas() {
        limparPolylinesRotas()

        _rotas.value.sortedBy {
            it.qtdOcorrenciasTotais
        }.forEachIndexed { i, rota ->
            exibirPolyline(rota, GogoodPolylines[i])


        }
        atualizarPosicaoCamera(
            latLng = polylines[0].points[0],
            angulo = 0f
        )
    }
    //endregion


    fun definirRaioBusca(): Double {

        val zoom = posicaoCameraMapa.zoom
        var radius = 3.0
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

    fun obterEnderecosPesquisadosRecentes(){
        viewModelScope.launch {
            mapRepository.obterEnderecosPesquisados().collect{
                enderecosRecentesPesquisados.value = it
            }
        }

    }

}
