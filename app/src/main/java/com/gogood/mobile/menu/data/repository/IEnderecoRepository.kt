package com.gogood.mobile.menu.data.repository

import com.gogood.mobile.home.domain.models.RotaHistoricoResponse
import com.gogood.mobile.menu.domain.models.EnderecoResponse
import retrofit2.Response

interface IEnderecoRepository {
    suspend fun obterEnderecosFavoritos(idUsuario: Int): Response<List<EnderecoResponse>>
    suspend fun obterHistoricoRota(idUsuario: Int): Response<List<RotaHistoricoResponse>>
}