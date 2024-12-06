package com.gogood.mobile.home.data.repository.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.gogood.mobile.home.data.repository.IMapRepository
import com.gogood.mobile.home.domain.models.BuscaEnderecoResponse
import com.gogood.mobile.home.domain.models.Candidate
import com.gogood.mobile.home.domain.models.Coordinates
import com.gogood.mobile.home.domain.models.CrimeQtd
import com.gogood.mobile.home.domain.models.Etapa
import com.gogood.mobile.home.domain.models.Geometry
import com.gogood.mobile.home.domain.models.LatLngOcorrencia
import com.gogood.mobile.home.domain.models.Location
import com.gogood.mobile.home.domain.models.QtdMes
import com.gogood.mobile.home.domain.models.RelatorioOcorrenciasResponse
import com.gogood.mobile.home.domain.models.RotaHistoricoRequest
import com.gogood.mobile.home.domain.models.RotaHistoricoResponse
import com.gogood.mobile.home.domain.models.RotaResponse
import com.gogood.mobile.home.domain.models.Viewport
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.ktx.model.markerOptions
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import retrofit2.Response

class MapRepositoryLocal(private val dataStore: DataStore<Preferences>): IMapRepository {
    val enderecoPreferencesKey = stringPreferencesKey("endereco")

    override suspend fun buscarEndereco(entrada: String): Response<BuscaEnderecoResponse> {
        return Response.success(
            BuscaEnderecoResponse(
            candidates = listOf(
                Candidate(
                    geometry = Geometry(
                        location = Location(lat = -23.5581213, lng = -46.661614),
                        viewport = Viewport(
                            northeast = Coordinates(lat = -23.55670402010727, lng = -46.66034207010728),
                            southwest = Coordinates(lat = -23.55940367989271, lng = -46.66304172989272)
                        )
                    )
                )
            ),
            status = "OK"
        )
        )
    }

    override suspend fun buscarRota(
        meioTransporte: String,
        origem: String,
        destino: String
    ): Response<List<RotaResponse>> {


        val resposta = Response.success(
           listOf(
               RotaResponse(
                   origem = "Rua Haddock Lobo, 595 - Cerqueira César, São Paulo - SP, 01414-001, Brasil",
                   destino = "Parque Ibirapuera - Av. Pedro Álvares Cabral - Vila Mariana, São Paulo - SP, 04094-050, Brasil",
                   distancia = 3.696,
                   duracao = "12 minutos",
                   horarioChegada = "16/11/2024 18:11:15",
                   horarioSaida = "16/11/2024 18:22:54",
                   qtdOcorrenciasTotais = 300,
                   polyline = "ldxnC`sx{Gt@v@l@s@`BqBnGsHfBsB`AmAl@y@hByB`EqE`EkFvCsD`EzDvKjKfJ|InM`MvApAn@l@KJuA~AzBnDtAtBv@}@hAqAPSz@~@hD`ExBbCPT`@e@lAqAv@y@xCcDpGeHvA}Ad@[jAm@z@]t@i@f@g@NUNFBBC\\IAIDMVCB",
                   etapas = emptyList()
               ),
               RotaResponse(
                   origem = "Rua Haddock Lobo, 595 - Cerqueira César, São Paulo - SP, 01414-001, Brasil",
                   destino = "Parque Ibirapuera - Av. Pedro Álvares Cabral - Vila Mariana, São Paulo - SP, 04094-050, Brasil",
                   distancia = 3.605,
                   duracao = "12 minutos",
                   horarioChegada = "16/11/2024 18:11:15",
                   horarioSaida = "16/11/2024 18:23:05",
                   qtdOcorrenciasTotais = 350,
                   polyline = "ldxnC`sx{GhJzJfDnDp@n@jEvEhGrGhBnBd@h@h@m@pBaCrBeCrA}A~AkBzCqD~E}FtA_BV[t@x@rArAj@n@jBjBf@n@j@v@xBjD|@pApHxLFJ`@e@X[~BgCdGuGnF{FlMoN~@{@vA{@XIx@a@l@e@RQV]DGHDFB@BEZIAGFQV?@",
                   etapas = emptyList()
               ),
               RotaResponse(
                   origem = "Rua Haddock Lobo, 595 - Cerqueira César, São Paulo - SP, 01414-001, Brasil",
                   destino = "Parque Ibirapuera - Av. Pedro Álvares Cabral - Vila Mariana, São Paulo - SP, 04094-050, Brasil",
                   distancia = 3.875,
                   duracao = "12 minutos",
                   horarioChegada = "16/11/2024 18:11:15",
                   horarioSaida = "16/11/2024 18:23:05",
                   qtdOcorrenciasTotais = 150,
                   polyline = "ldxnC`sx{Gw@{@}@cARUf@k@\\a@jB{B`CqCvFwGbDuDxAgBr@y@rBiCfF{Gf@o@b@b@nChCrJjJtKfKlL|KxEtEvApAn@l@KJuA~AzBnDtAtBv@}@hAqAPSz@~@hD`ExBbCPT`@e@lAqAv@y@xCcDpGeHvA}Ad@[jAm@z@]jA{@PUNUNFBBC\\IAIDGJIN",
                   etapas = emptyList()
               )
           )
        )


        return resposta
    }

    override suspend fun buscarRelatorioRaio(
        lat: Double,
        lng: Double,
        raio: Double
    ): Response<RelatorioOcorrenciasResponse> {
        return Response.success(
            RelatorioOcorrenciasResponse(
                listOf(

                    CrimeQtd("ROUBO", 50),
                    CrimeQtd("FURTO", 150),
                    CrimeQtd("AGRESSÃO", 20)
                ),
                qtdOcorrencias = 150,
                qtdMes = QtdMes(
                    janeiro = 0,
                    fevereiro = 0,
                    marco = 0,
                    abril = 0,
                    maio = 150,
                    junho = 0,
                    julho = 0,
                    agosto = 0,
                )
            )
        )
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

    override suspend fun salvarRotaHistorico(rotaNova: RotaHistoricoRequest): Response<RotaHistoricoResponse> {
        TODO("Not yet implemented")
    }

    override suspend fun obterEnderecosPesquisados(): Flow<List<String>>{
        return dataStore.data.map { preferences ->
            preferences[enderecoPreferencesKey]?.split(";")?.reversed() ?: emptyList()
        }
    }


    override suspend fun obterOcorrenciasRaio(
        lat: Double,
        lng: Double,
        raio: Double
    ): Response<List<LatLng>> {
        val ocorrenciasRaioResponse =(
            listOf(
                LatLng(-46.39255806, -23.57828364),
                LatLng(-46.39423897, -23.58013943),
                LatLng(-46.39396789, -23.57890227),
                LatLng(-46.39394894, -23.57884974),
                LatLng(-46.39050282, -23.58125596),
                LatLng(-46.39081323, -23.58161366),
                LatLng(-46.39440356, -23.58080639),
                LatLng(-46.39440726, -23.58082484),
                LatLng(-46.3899165, -23.5801699),
                LatLng(-46.3946403, -23.57978929),
                LatLng(-46.39012245, -23.58099533),
                LatLng(-46.3898884, -23.5801579),
                LatLng(-46.3898884, -23.5801579),
                LatLng(-46.3898884, -23.5801579),
                LatLng(-46.39469061, -23.57992669),
                LatLng(-46.3900739, -23.58096337),
                LatLng(-46.39433788, -23.58125055),
                LatLng(-46.389993, -23.58091009),
                LatLng(-46.39444605, -23.57875883),
                LatLng(-46.39115977, -23.57787727),
                LatLng(-46.38975028, -23.58075028),
                LatLng(-46.3911596, -23.5778268),
                LatLng(-46.3911596, -23.5778268),
                LatLng(-46.39080054, -23.58214134),
                LatLng(-46.39180164, -23.5825552),
                LatLng(-46.39180164, -23.5825552),
                LatLng(-46.39203141, -23.58262739),
                LatLng(-46.39431283, -23.57819383),
                LatLng(-46.38944233, -23.58054574),
                LatLng(-46.38944233, -23.58054574),
                LatLng(-46.39170411, -23.57716538),
                LatLng(-46.39076522, -23.58265949),
                LatLng(-46.39576496, -23.5808519),
                LatLng(-46.39576496, -23.5808519),
                LatLng(-46.3930215, -23.58333459),
                LatLng(-46.393155, -23.5834788),
                LatLng(-46.3940502, -23.58321129),
                LatLng(-46.3921911, -23.5759757),
                LatLng(-46.39218428, -23.57588406),
                LatLng(-46.39218428, -23.57588406),
                LatLng(-46.39218428, -23.57588406),
                LatLng(-46.39218428, -23.57588406),
                LatLng(-46.39218428, -23.57588406),
                LatLng(-46.39218428, -23.57588406),
                LatLng(-46.39296088, -23.58439029),
                LatLng(-46.39064509, -23.58418332)
            )
        )

        return Response.success(ocorrenciasRaioResponse)
    }
}