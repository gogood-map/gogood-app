package com.gogood.mobile.home.presentation.composables

import android.Manifest
import android.annotation.SuppressLint
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NearMe
import androidx.compose.material.icons.filled.RoundaboutRight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.gogood.mobile.home.presentation.viewmodels.MapaViewModel
import com.gogood.mobile.menu.apresentation.composables.Menu
import com.gogood.mobile.ui.theme.GogoodGray
import com.gogood.mobile.ui.theme.GogoodWhite
import com.gogood.mobile.utils.LocalizacaoObserver
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.MapsInitializer
import kotlinx.coroutines.delay
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel

@SuppressLint("MissingPermission")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Mapa(navController: NavController) {
    val context = LocalContext.current
    val localizacaoObserver = koinInject<LocalizacaoObserver>()

    val getPermission = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            localizacaoObserver.permissaoLocalizacao.value = true
        }
    }
    SideEffect {
        getPermission.launch(Manifest.permission.ACCESS_FINE_LOCATION)
    }

    val mapaViewModel: MapaViewModel = koinViewModel()

    val isConnected by mapaViewModel.contectado.collectAsState()
    val isLoading = mapaViewModel.isLoading

    var buscaEnderecoState = mapaViewModel.entradaBuscaEndereco
    var origemState = mapaViewModel.entradaOrigemRota
    var destinoState = mapaViewModel.entradaDestinoRota

    var showMenu by remember { mutableStateOf(false) }

    val mapView = remember { MapView(context) }

    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false,
    )


    DisposableEffect(mapView) {
        mapView.onCreate(null)
        mapView.onResume()
        MapsInitializer.initialize(context)
        onDispose {
            mapView.onPause()
            mapView.onDestroy()
        }
    }
    if (isLoading){
       Loading()
    }
    else if (isConnected) {
        LaunchedEffect(localizacaoObserver.permissaoLocalizacao.collectAsState()) {
            mapaViewModel.observarUsuario()
        }
        AndroidView(
            factory = { mapView },
            modifier = Modifier.fillMaxSize(),
        ) { map ->
            map.getMapAsync { mapInstance ->
                mapInstance.setOnCameraIdleListener {
                    mapaViewModel.atualizarPosicaoCamera(mapInstance.cameraPosition)

                    mapaViewModel.buscarOcorrenciasRaio()

                }
                mapInstance.isBuildingsEnabled = false
                if(localizacaoObserver.permissaoLocalizacao.value){
                    mapInstance.uiSettings.isMyLocationButtonEnabled = false
                    mapInstance.isMyLocationEnabled = true
                }else{
                    mapInstance.isMyLocationEnabled = false
                }

                mapaViewModel.mapa = mapInstance
                mapaViewModel.buscarOcorrenciasRaio()
            }
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Row(
                modifier = Modifier
                    .padding(top = 20.dp, start = 16.dp, end = 16.dp)
                    .fillMaxWidth(),

                ) {

                BotaoMenu(modifier = Modifier.padding(top = 8.dp)) {
                    showMenu = true
                }

                Spacer(modifier = Modifier.width(24.dp))

                AnimatedVisibility(
                    visible = mapaViewModel.isSearchAddress,
                    enter = slideInVertically(initialOffsetY = { it }),
                    exit = slideOutVertically(targetOffsetY = { it })
                ) {
                    CaixaPesquisaEndereco(searchState = buscaEnderecoState) {
                        mapaViewModel.buscarEndereco(buscaEnderecoState.value)
                        buscaEnderecoState.value = ""

                    }
                }

                AnimatedVisibility(
                    visible = mapaViewModel.isSearchRoute,
                    enter = slideInVertically(initialOffsetY = { -it }),
                    exit = slideOutVertically(targetOffsetY = { -it })
                ) {
                    CaixaPesquisaRota(
                        destino = destinoState,
                        origem = origemState,
                    ) {
                        mapaViewModel.showBottomSheet = !mapaViewModel.showBottomSheet
                    }
                }


            }

            FloatingActionButton(
                onClick = {
                    mapaViewModel.isSearchRoute = !mapaViewModel.isSearchRoute
                    mapaViewModel.isSearchAddress = !mapaViewModel.isSearchRoute
                    if(mapaViewModel.isSearchAddress){
                        mapaViewModel.alterarAnguloCamera()
                    }
                },
                containerColor = GogoodGray,
                contentColor = GogoodWhite,
                shape = CircleShape,
                modifier = Modifier
                    .padding(bottom = 48.dp, end = 16.dp)
                    .size(64.dp)
                    .align(Alignment.End),
            ) {
                Icon(
                    if (!mapaViewModel.isSearchRoute)
                        Icons.Filled.RoundaboutRight

                    else
                        Icons.Default.NearMe, "Small floating action button."

                )
            }
        }

        AnimatedVisibility(
            visible = showMenu,
            enter = slideInHorizontally(initialOffsetX = { -it }),
            exit = slideOutHorizontally(targetOffsetX = { -it })
        ) {
            Menu(
                navController = navController,
                onClose = { showMenu = false },
            )
        }

        if(mapaViewModel.showBottomSheet){
            ModalBottomSheet(modifier = Modifier.fillMaxHeight(),
                sheetState = sheetState,
                onDismissRequest = { mapaViewModel.showBottomSheet = false }) {
                Bandeja()
            }
        }

    }
    else{
        AvisoSemConexao()
    }
}






@Preview
@Composable
fun HeatmapPreview(modifier: Modifier = Modifier) {
    Mapa(navController = NavHostController(LocalContext.current))
}