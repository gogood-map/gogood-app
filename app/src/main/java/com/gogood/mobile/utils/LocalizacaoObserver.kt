package com.gogood.mobile.utils

import android.annotation.SuppressLint
import android.content.Context
import android.location.LocationManager
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.isActive
import kotlin.coroutines.coroutineContext

class LocalizacaoObserver(context: Context) {
    private val fusedLocationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)


    var permissaoLocalizacao = MutableStateFlow(false)



    private val locationRequest = LocationRequest.Builder(

        Priority.PRIORITY_HIGH_ACCURACY, 1000L
    ).apply {
        setMinUpdateIntervalMillis(500L)
        setWaitForAccurateLocation(true)
    }.build()



    @SuppressLint("MissingPermission")
    fun observerLocalizacao(): Flow<LatLng> = callbackFlow {
        val locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                locationResult.lastLocation?.let { location ->
                    trySend(LatLng(location.latitude, location.longitude))
                }
            }
        }
        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null)

        awaitClose {
            fusedLocationClient.removeLocationUpdates(locationCallback)
        }
    }
}