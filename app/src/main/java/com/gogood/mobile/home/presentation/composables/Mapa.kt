package com.gogood.mobile.home.presentation.composables

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.RoundaboutRight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.gogood.mobile.home.presentation.viewmodels.MainViewModel
import com.gogood.mobile.home.presentation.viewmodels.MapaViewModel
import com.gogood.mobile.menu.apresentation.composables.Menu
import com.gogood.mobile.ui.theme.GogoodGray
import com.gogood.mobile.ui.theme.GogoodGreen
import com.gogood.mobile.ui.theme.GogoodWhite
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.MapsInitializer
import com.google.android.gms.maps.model.TileOverlayOptions
import com.google.maps.android.heatmaps.Gradient
import com.google.maps.android.heatmaps.HeatmapTileProvider
import com.google.maps.android.heatmaps.WeightedLatLng
import org.koin.compose.viewmodel.koinViewModel

@SuppressLint("MissingPermission")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Mapa(navController: NavController) {
    val context = LocalContext.current
    val mapaViewModel: MapaViewModel = koinViewModel()
    val mainViewModel = koinViewModel<MainViewModel>()
    val isConnected by mainViewModel.contectado.collectAsState()



    var searchState by remember {
        mutableStateOf("")
    }
    var origem by remember {
        mutableStateOf("")
    }
    var destino by remember {
        mutableStateOf("")
    }


    var showMenu by remember { mutableStateOf(false) }




    val mapView = remember { MapView(context) }


    DisposableEffect(mapView) {
        mapView.onCreate(null)
        mapView.onResume()
        MapsInitializer.initialize(context)
        onDispose {
            mapView.onPause()
            mapView.onDestroy()
        }
    }
    if(isConnected){
        AndroidView(

            factory = { mapView },
            modifier = Modifier.fillMaxSize(),
        ){map->
            map.getMapAsync { mapInstance ->

                mapInstance.moveCamera(CameraUpdateFactory.newCameraPosition(
                    mapaViewModel.posicaoCamera
                ))
                mapInstance.setOnCameraIdleListener {
                    mapaViewModel.atualizarPosicaoCamera(mapInstance.cameraPosition)
                    mapaViewModel.buscarOcorrenciasRaio()
                    if(mapaViewModel.coordenadasOcorrenciasMapaDeCalor.value.isNotEmpty()){
                        atualizarMapaDeCalor(mapInstance, mapaViewModel.coordenadasOcorrenciasMapaDeCalor.value)
                    }

                }
                mapInstance.uiSettings.isMyLocationButtonEnabled= true
                mapInstance.isMyLocationEnabled = true

                if(mapaViewModel.coordenadasOcorrenciasMapaDeCalor.value.isNotEmpty()){
                    atualizarMapaDeCalor(mapInstance, mapaViewModel.coordenadasOcorrenciasMapaDeCalor.value)
                }
            }
        }
    }
    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween){

        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            BotaoMenu {
                showMenu = true
            }
            Spacer(modifier = Modifier.width(24.dp))
            CaixaPesquisaEndereco(
                searchState = searchState,
                onValueChange = { searchState = it },
                onDone = { }
            )
        }

        FloatingActionButton(
            onClick = {  },
            containerColor = GogoodGray,
            contentColor = GogoodWhite,
            shape = CircleShape,
            modifier = Modifier
                .padding(bottom = 48.dp, end = 16.dp)
                .size(64.dp)
                .align(Alignment.End),
        ) {
            Icon(Icons.Filled.RoundaboutRight, "Small floating action button.")
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
}

private fun atualizarMapaDeCalor(googleMap: GoogleMap?, weightedLatLngs: List<WeightedLatLng>) {
    googleMap?.clear()

    val cores = intArrayOf(
        Color.YELLOW,
        Color.rgb(255, 165, 0),

        Color.RED,
    )
    val pontosIntensidade = floatArrayOf(0.1f,  0.3f, 1f)
    val heatmapTileProvider = HeatmapTileProvider.Builder()
        .weightedData(weightedLatLngs)
        .gradient(Gradient(cores, pontosIntensidade))
        .maxIntensity(7.0)
        .build()

    googleMap?.addTileOverlay(TileOverlayOptions().tileProvider(heatmapTileProvider))

}


@Composable
fun BotaoMenu(modifier: Modifier = Modifier, click: ()->Unit){
    IconButton(
        onClick = click,
        modifier = Modifier
            .size(32.dp)
            .shadow(8.dp, CircleShape)
            .background(shape = CircleShape, color = GogoodGreen)
    ) {
        Icon(
            imageVector = Icons.Default.Menu,
            contentDescription = "Pesquisar",
            tint = androidx.compose.ui.graphics.Color.White
        )
    }
}

@Preview
@Composable
fun HeatmapPreview(modifier: Modifier = Modifier) {
    Mapa(navController = NavHostController(LocalContext.current))
}