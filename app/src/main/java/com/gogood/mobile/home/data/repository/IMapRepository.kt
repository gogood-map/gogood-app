package com.gogood.mobile.home.data.repository

import com.gogood.mobile.home.domain.models.BuscaEnderecoResponse
import com.gogood.mobile.home.domain.models.LatLngOcorrencia
import com.gogood.mobile.home.domain.models.RelatorioOcorrenciasResponse
import com.gogood.mobile.home.domain.models.RotaResponse
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface IMapRepository {

    suspend fun obterOcorrenciasRaio(lat: Double, lng: Double, raio: Double):Response<List<LatLng>>
    suspend fun buscarEndereco(entrada: String): Response<BuscaEnderecoResponse>
    suspend fun buscarRota(meioTransporte:String,origem: String, destino: String): Response<List<RotaResponse>>
    suspend fun buscarRelatorioRaio(lat:Double, lng: Double, raio: Double):Response<RelatorioOcorrenciasResponse>
    suspend fun obterEnderecosPesquisados(): Flow<List<String>>
    suspend fun salvarEnderecoPesquisado(endereco: String)
}
