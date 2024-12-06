package com.gogood.mobile.home.domain.usecases

import com.gogood.mobile.home.domain.models.RotaHistoricoRequest
import com.gogood.mobile.home.domain.models.RotaHistoricoResponse

interface ISalvarRotaHistoricoUseCase {
    suspend operator fun invoke(rotaNova: RotaHistoricoRequest): Result<RotaHistoricoResponse>;
}