package com.gogood.mobile.home.domain.usecases

import com.gogood.mobile.home.domain.models.RotaResponse

interface IBuscaRotaUseCase {
    suspend operator fun invoke(meioTransporte:String,origem: String, destino: String):Result<List<RotaResponse>>
}