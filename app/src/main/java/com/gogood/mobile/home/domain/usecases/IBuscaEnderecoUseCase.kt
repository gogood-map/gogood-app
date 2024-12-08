package com.gogood.mobile.home.domain.usecases

import com.gogood.mobile.home.domain.models.BuscaEnderecoResponse

interface IBuscaEnderecoUseCase {
    suspend operator fun invoke(entrada: String): Result<BuscaEnderecoResponse>
}
