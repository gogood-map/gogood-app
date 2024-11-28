package com.gogood.mobile.home.presentation.composables

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AltRoute
import androidx.compose.material.icons.filled.CrisisAlert
import androidx.compose.material.icons.sharp.Directions
import androidx.compose.material.icons.sharp.LocationOn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.gogood.mobile.home.presentation.viewmodels.MapaViewModel
import com.gogood.mobile.menu.apresentation.composables.Menu
import com.gogood.mobile.ui.theme.GogoodGray
import com.gogood.mobile.ui.theme.GogoodGreen
import com.gogood.mobile.ui.theme.GogoodWhite
import com.gogood.mobile.utils.ILocalizacaoUtils
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.MapsInitializer
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel

@SuppressLint("MissingPermission")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Mapa(navController: NavController) {
    val context = LocalContext.current

    val mapaViewModel: MapaViewModel = koinViewModel()


    val buscaEnderecoState = mapaViewModel.entradaBuscaEndereco
    val origemState = mapaViewModel.entradaOrigemRota
    val destinoState = mapaViewModel.entradaDestinoRota

    val localizacaoObserver = koinInject<ILocalizacaoUtils>()

    var showMenu by remember { mutableStateOf(false) }

    val mapView = remember { MapView(context) }

    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false,
    )

    val rotas by mapaViewModel.rotas.collectAsState()
    DisposableEffect(mapView) {
        mapView.onCreate(null)
        mapView.onResume()
        MapsInitializer.initialize(context)
        onDispose {
            mapView.onPause()
            mapView.onDestroy()
        }
    }
    LaunchedEffect(localizacaoObserver.permissaoLocalizacao.collectAsState()) {
        mapaViewModel.observarUsuario()
    }


    AndroidView(
        factory = { mapView },
        modifier = Modifier.fillMaxSize(),
    ) { map ->
        map.getMapAsync { mapInstance ->
            mapInstance.moveCamera(
                CameraUpdateFactory.newCameraPosition(
                mapaViewModel.posicaoCameraBusca
            ))


            mapInstance.setOnCameraIdleListener {
                mapaViewModel.atualizarPosicaoCamera(mapInstance.cameraPosition)

                mapaViewModel.buscarOcorrenciasRaio()
                mapaViewModel.buscarRelatorioRaio()
                mapaViewModel.atualizarMapaCalor()
            }
            if(localizacaoObserver.permissaoLocalizacao.value){
                mapInstance.isMyLocationEnabled = true
            }

            mapInstance.isBuildingsEnabled = false

            mapaViewModel.mapa = mapInstance
            mapaViewModel.buscarOcorrenciasRaio()
            mapaViewModel.buscarRelatorioRaio()
            mapaViewModel.atualizarMapaCalor()
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
                    mapaViewModel.limparRotas()
                    mapaViewModel.limparPolylinesRotas()
                    mapaViewModel.showBottomSheet = !mapaViewModel.showBottomSheet
                }
            }


        }
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp, start = 16.dp, end = 16.dp),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.SpaceBetween
        ){

            Column (verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,

                ){
                if(rotas.isNotEmpty()){
                    SmallFloatingActionButton(
                        onClick = {
                            mapaViewModel.showBottomSheet = true
                        },
                        containerColor = GogoodGreen,
                        contentColor = GogoodWhite,
                        shape = CircleShape,) {
                        Icon(
                            imageVector = Icons.Default.AltRoute,
                            contentDescription = "Opções de Rotas"
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }
                FloatingActionButton(
                    onClick = {
                        mapaViewModel.showBottomSheet = true
                    },
                    containerColor = GogoodGray,
                    contentColor = GogoodWhite,
                    shape = CircleShape,
                    modifier = Modifier

                        .size(64.dp)

                ) {
                    Icon(

                        Icons.Default.CrisisAlert, "Small floating action button."

                    )
                }
            }

            FloatingActionButton(
                onClick = {
                    mapaViewModel.isSearchRoute = !mapaViewModel.isSearchRoute
                    mapaViewModel.isSearchAddress = !mapaViewModel.isSearchRoute
                },
                containerColor = GogoodGray,
                contentColor = GogoodWhite,
                shape = CircleShape,
                modifier = Modifier

                    .size(64.dp)

            ) {
                Icon(
                    if (!mapaViewModel.isSearchRoute)
                        Icons.Sharp.Directions
                    else
                        Icons.Sharp.LocationOn, "Small floating action button."

                )
            }




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
        ModalBottomSheet(modifier = Modifier.height(412.dp),
            sheetState = sheetState,
            onDismissRequest = { mapaViewModel.showBottomSheet = false }) {
            Bandeja()
        }
    }

}