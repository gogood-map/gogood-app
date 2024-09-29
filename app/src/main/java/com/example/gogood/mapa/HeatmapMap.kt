package com.example.gogood.mapa

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.gogood.menu.Menu
import com.example.gogood.navegacao.Menu
import com.example.gogood.ui.componentes.bandeja.Bandeja
import com.example.gogood.ui.theme.GoGoodTheme
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.TileOverlayOptions
import com.google.gson.Gson
import com.google.maps.android.heatmaps.HeatmapTileProvider
import com.google.maps.android.heatmaps.WeightedLatLng
import okhttp3.ResponseBody
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HeatmapMap(navController: NavController) {
    val context = LocalContext.current
    val mapView = remember { MapView(context) }
    var googleMap by remember { mutableStateOf<GoogleMap?>(null) }
    var weightedLatLngs by remember { mutableStateOf<List<WeightedLatLng>>(emptyList()) }

    var showBottomSheet by remember { mutableStateOf(false) }
    val bottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false
    )

    var searchState by remember { mutableStateOf("") }

    DisposableEffect(mapView) {
        mapView.onCreate(null)
        onDispose {
            mapView.onDestroy()
        }
    }

    LaunchedEffect(mapView) {
        mapView.getMapAsync { map ->
            googleMap = map
            fetchLocations { locations ->
                weightedLatLngs = locations
                setupHeatmap(googleMap, weightedLatLngs)
            }
        }
    }

    AndroidView(
        factory = { mapView },
        modifier = Modifier.fillMaxSize()
    )

    Box(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { navController.navigate(Menu) },
                modifier = Modifier
                    .size(32.dp)
                    .shadow(8.dp, CircleShape)
                    .background(shape = CircleShape, color = MaterialTheme.colorScheme.primary)
            ) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Pesquisar",
                    tint = Color.White
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            BasicTextField(
                value = searchState,
                onValueChange = { searchState = it },
                modifier = Modifier
                    .weight(5f)
                    .height(45.dp)
                    .border(1.dp, Color.Black, RoundedCornerShape(32.dp))
                    .background(Color.White, RoundedCornerShape(32.dp))
                    .padding(start = 15.dp, top = 15.dp),
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = {
                        showBottomSheet = true
                    }
                )
            )
        }
    }

    if (showBottomSheet) {
        ModalBottomSheet(
            sheetState = bottomSheetState,
            onDismissRequest = { showBottomSheet = false }
        ) {
            Bandeja(
                navController = navController,
                abrir = true
            )
        }
    }
}

private fun setupHeatmap(googleMap: GoogleMap?, weightedLatLngs: List<WeightedLatLng>) {
    val heatmapTileProvider = HeatmapTileProvider.Builder()
        .weightedData(weightedLatLngs)
        .build()

    googleMap?.addTileOverlay(TileOverlayOptions().tileProvider(heatmapTileProvider))
    googleMap?.moveCamera(
        CameraUpdateFactory.newLatLngZoom(
            LatLng(
                -23.5578763,
                -46.6616295
            ), 10f
        )
    )
}

private fun fetchLocations(onSuccess: (List<WeightedLatLng>) -> Unit) {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://66f20350415379191552cec4.mockapi.io/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService = retrofit.create(ApiService::class.java)

    apiService.getGenericData("api/registros").enqueue(object : Callback<ResponseBody> {
        override fun onResponse(
            call: Call<ResponseBody>,
            response: Response<ResponseBody>
        ) {
            if (response.isSuccessful) {
                response.body()?.string()?.let { body ->
                    val locations = parseJsonToWeightedLatLng(body)
                    onSuccess(locations)
                }
            }
        }

        override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
            // Tratar erro
        }
    })
}

private fun parseJsonToWeightedLatLng(json: String): List<WeightedLatLng> {
    val gson = Gson()
    val data = gson.fromJson(json, Array<LatLngData>::class.java)
    return data.map {
        WeightedLatLng(
            LatLng(it.latitude.toDouble(), it.longitude.toDouble()),
            1.0
        ) // peso padrão
    }
}

data class LatLngData(val latitude: String, val longitude: String, val id: String)

interface ApiService {
    @GET
    fun getGenericData(@Url url: String): Call<ResponseBody>
}

@Preview
@Composable
fun HeatmapPreview(modifier: Modifier = Modifier) {
    HeatmapMap(navController = NavHostController(LocalContext.current))
}
