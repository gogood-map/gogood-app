package com.gogood.mobile.home.presentation.stateholders

import com.google.android.gms.maps.model.LatLng

sealed class MainStateHolder {
    data object Loading: MainStateHolder()
    data class Content(val coordenadasOcorrenciasMapaDeCalor: List<LatLng> = emptyList()): MainStateHolder()
    data object NoConnection: MainStateHolder()
    data class Error(val titulo: String, val message: String): MainStateHolder()
}