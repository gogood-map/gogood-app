package com.gogood.mobile.home.presentation.composables

import android.graphics.Color
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.gogood.mobile.menu.apresentation.composables.Menu
import com.gogood.mobile.home.presentation.viewmodels.MapaViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.TileOverlayOptions
import com.google.maps.android.heatmaps.Gradient
import com.google.maps.android.heatmaps.HeatmapTileProvider
import com.google.maps.android.heatmaps.WeightedLatLng
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Mapa(navController: NavController, mapaViewModel: MapaViewModel = koinViewModel()) {



    val context = LocalContext.current
    val mapView = remember { MapView(context) }
    var googleMap by remember { mutableStateOf<GoogleMap?>(null) }


    var showBottomSheet by remember { mutableStateOf(false) }
    val bottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false
    )

    var searchState by remember { mutableStateOf("") }
    var showMenu by remember { mutableStateOf(false) }


    DisposableEffect(mapView) {
        mapView.onCreate(null)
        onDispose {
            mapView.onPause()
            mapView.onDestroy()

        }
    }

    LaunchedEffect(mapView) {
        mapView.getMapAsync { map ->
            googleMap = map
            map.moveCamera(CameraUpdateFactory.newCameraPosition(
                mapaViewModel.posicaoCamera
            ))
            map.setOnCameraIdleListener {
                mapaViewModel.atualizarPosicaoCamera(map.cameraPosition)
                mapaViewModel.buscarOcorrenciasRaio()
                if(mapaViewModel.coordenadasOcorrenciasMapaDeCalor.value.isNotEmpty()){
                    atualizarMapaDeCalor(googleMap, mapaViewModel.coordenadasOcorrenciasMapaDeCalor.value)
                }

            }
            if(mapaViewModel.coordenadasOcorrenciasMapaDeCalor.value.isNotEmpty()){
                atualizarMapaDeCalor(googleMap, mapaViewModel.coordenadasOcorrenciasMapaDeCalor.value)
            }
        }
    }

    AndroidView(
        factory = { mapView },
        modifier = Modifier.fillMaxSize(),
    )

    CaixaPesquisa(
        onShowMenu = { showMenu = true },
        searchState = searchState,
        onValueChange = { searchState = it },
        onDone = { showBottomSheet = true }
    )

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


@Preview
@Composable
fun HeatmapPreview(modifier: Modifier = Modifier) {
    Mapa(navController = NavHostController(LocalContext.current))
}