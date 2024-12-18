package com.gogood.mobile.home.domain.usecases

import com.gogood.mobile.home.domain.models.LatLngOcorrencia
import com.google.android.gms.maps.model.LatLng

interface IObterCoordenadasOcorrenciaRaioUseCase{
    suspend operator fun invoke(latLng: LatLng, raio: Double): Result<List<LatLng>>
}