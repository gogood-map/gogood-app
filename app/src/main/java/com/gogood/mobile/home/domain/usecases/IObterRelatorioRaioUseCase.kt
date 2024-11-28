package com.gogood.mobile.home.domain.usecases

import com.gogood.mobile.home.domain.models.RelatorioOcorrenciasResponse
import com.google.android.gms.maps.model.LatLng

interface IObterRelatorioRaioUseCase {
    suspend operator fun invoke(latLng: LatLng, raio: Double): Result<RelatorioOcorrenciasResponse>
}