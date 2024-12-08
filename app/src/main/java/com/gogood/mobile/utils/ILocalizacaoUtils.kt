package com.gogood.mobile.utils

import android.location.Location
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

interface ILocalizacaoUtils {
    fun observerLocalizacao(): Flow<Location>
    var permissaoLocalizacao: MutableStateFlow<Boolean>
    fun calcularDistancia(latLng1: LatLng, latLng2: LatLng): Double
}