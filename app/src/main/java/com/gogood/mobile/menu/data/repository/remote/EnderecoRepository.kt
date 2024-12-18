package com.gogood.mobile.menu.data.repository.remote

import com.gogood.mobile.home.domain.models.RotaHistoricoResponse
import com.gogood.mobile.menu.data.repository.IEnderecoRepository
import com.gogood.mobile.menu.domain.models.EnderecoResponse
import com.gogood.mobile.menu.domain.services.EnderecoService
import retrofit2.Response

class EnderecoRepository(private val enderecoService: EnderecoService): IEnderecoRepository {
    override suspend fun obterEnderecosFavoritos(idUsuario: Int): Response<List<EnderecoResponse>> {
        return enderecoService.obterEnderecosFavoritosUsuario(idUsuario)
    }

    override suspend fun obterHistoricoRota(idUsuario: Int): Response<List<RotaHistoricoResponse>> {
        return enderecoService.obterHistoricoRotasUsuario(idUsuario)
    }
}