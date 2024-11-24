package com.gogood.mobile.home.presentation.stateholders

import com.gogood.mobile.home.domain.models.RelatorioOcorrenciasResponse
import com.google.android.gms.maps.model.LatLng
import org.koin.core.logger.MESSAGE

sealed class MainStateHolder {
    data object Loading: MainStateHolder()
    data class Content(val coordenadasOcorrenciasMapaDeCalor: List<LatLng> = emptyList()): MainStateHolder()
    data object NoConnection: MainStateHolder()
    data class Error(val message: String): MainStateHolder()
}