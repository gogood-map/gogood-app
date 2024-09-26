package com.example.gogood.mapa

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
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

@Composable
fun HeatmapMap(navController: NavHostController) {
    val context = LocalContext.current
    val mapView = remember { MapView(context) }
    var googleMap by remember { mutableStateOf<GoogleMap?>(null) }
    var weightedLatLngs by remember { mutableStateOf<List<WeightedLatLng>>(emptyList()) }

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
}

private fun setupHeatmap(googleMap: GoogleMap?, weightedLatLngs: List<WeightedLatLng>) {
    val heatmapTileProvider = HeatmapTileProvider.Builder()
        .weightedData(weightedLatLngs)
        .build()

    googleMap?.addTileOverlay(TileOverlayOptions().tileProvider(heatmapTileProvider))
    googleMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(-23.5578763, -46.6616295), 10f))
}

private fun fetchLocations(onSuccess: (List<WeightedLatLng>) -> Unit) {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://66f20350415379191552cec4.mockapi.io/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService = retrofit.create(ApiService::class.java)

    apiService.getGenericData("api/registros").enqueue(object : Callback<ResponseBody> {
        override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
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
        WeightedLatLng(LatLng(it.latitude.toDouble(), it.longitude.toDouble()), 1.0) // peso padrão
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
