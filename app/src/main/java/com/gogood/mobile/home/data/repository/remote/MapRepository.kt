package com.gogood.mobile.home.data.repository.remote

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.gogood.mobile.home.domain.services.MapsService
import com.gogood.mobile.home.data.repository.IMapRepository
import com.gogood.mobile.home.domain.models.BuscaEnderecoResponse
import com.gogood.mobile.home.domain.models.RelatorioOcorrenciasResponse
import com.gogood.mobile.home.domain.models.RotaResponse
import com.gogood.mobile.home.domain.services.GooglePlacesService
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import retrofit2.Response

class MapRepository(private val service: MapsService,
                    private val googlePlacesService: GooglePlacesService,
                    private val dataStore: DataStore<Preferences>
): IMapRepository {
    val enderecoPreferencesKey = stringPreferencesKey("endereco")
    override suspend fun obterOcorrenciasRaio(
        lat: Double,
        lng: Double,
        raio: Double
    ): Response<List<LatLng>> {
       return service.obterOcorrenciasRaio(lat, lng, raio)
    }

    override suspend fun buscarEndereco(entrada: String): Response<BuscaEnderecoResponse> {
        return googlePlacesService.buscarEndereco(entrada)
    }

    override suspend fun buscarRota(
        meioTransporte: String,
        origem: String,
        destino: String
    ): Response<List<RotaResponse>> {
        return service.obterRota(meioTransporte, origem, destino)
    }

    override suspend fun buscarRelatorioRaio(
        lat: Double,
        lng: Double,
        raio: Double
    ): Response<RelatorioOcorrenciasResponse> {
        return service.obterRelatorioRaio(lat, lng, raio)
    }

    override suspend fun salvarEnderecoPesquisado(endereco: String){
        dataStore.edit { preferences ->
            val listaEnderecosPesquisados = preferences[enderecoPreferencesKey]
                ?.split(";")
                ?.toMutableList() ?: mutableListOf()

            if(listaEnderecosPesquisados.size > 10){
                listaEnderecosPesquisados.removeFirst()
            }

            if(!listaEnderecosPesquisados.contains(endereco)){
                listaEnderecosPesquisados.add(endereco.replace(";", ""))
            }

            preferences[enderecoPreferencesKey] = listaEnderecosPesquisados.joinToString(";")

        }
    }

    override suspend fun obterEnderecosPesquisados(): Flow<List<String>>{
        return dataStore.data.map { preferences ->
            preferences[enderecoPreferencesKey]?.split(";")?.reversed() ?: emptyList()
        }
    }




}