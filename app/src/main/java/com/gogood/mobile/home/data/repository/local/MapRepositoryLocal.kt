package com.gogood.mobile.home.data.repository.local

import com.gogood.mobile.home.data.repository.IMapRepository
import com.gogood.mobile.home.domain.models.BuscaEnderecoResponse
import com.gogood.mobile.home.domain.models.Etapa

import com.gogood.mobile.home.domain.models.OcorrenciasRaioResponse
import com.gogood.mobile.home.domain.models.RotaResponse
import retrofit2.Response

class MapRepositoryLocal(): IMapRepository {


    override suspend fun buscarEndereco(entrada: String): Response<BuscaEnderecoResponse> {
        TODO()
    }

    override suspend fun buscarRota(
        meioTransporte: String,
        origem: String,
        destino: String
    ): Response<List<RotaResponse>> {

        val rota = RotaResponse(
            origem = "R. Cachoeira das Garças, 509 - Conj. Hab. Sitio Conceicao, São Paulo - SP, 08473-010, Brasil",
            destino = "R. Cachoeira das Garças, 7A - Conj. Hab. Sitio Conceicao, São Paulo - SP, 08473-010, Brasil",
            distancia = 0.477,
            duracao = "1 min",
            horarioSaida = "13/11/2024 17:22:45",
            horarioChegada = "13/11/2024 17:23:44",
            qtdOcorrenciasTotais = 0,
            polyline = "r}|nCd_dzGmEJoAC_Da@iC[cAOq@EU@uBj@",
            etapas = listOf(
                Etapa(instrucao = "Siga na direção <b>norte</b> na <b>R. Cachoeira das Garças</b> em direção a <b>R. Velho Tema</b><div style=\"font-size:0.9em\">O destino estará à direita</div>")
            ),
        )

        val resposta = Response.success(
           listOf(rota)
        )

        return resposta
    }


    override suspend fun obterOcorrenciasRaio(
        lat: Double,
        lng: Double,
        raio: Double
    ): Response<OcorrenciasRaioResponse> {
        val ocorrenciasRaioResponse = OcorrenciasRaioResponse(
            qtdOcorrencias = 46,
            coordenadasOcorrencias = listOf(
                listOf(-46.39255806, -23.57828364),
                listOf(-46.39423897, -23.58013943),
                listOf(-46.39396789, -23.57890227),
                listOf(-46.39394894, -23.57884974),
                listOf(-46.39050282, -23.58125596),
                listOf(-46.39081323, -23.58161366),
                listOf(-46.39440356, -23.58080639),
                listOf(-46.39440726, -23.58082484),
                listOf(-46.3899165, -23.5801699),
                listOf(-46.3946403, -23.57978929),
                listOf(-46.39012245, -23.58099533),
                listOf(-46.3898884, -23.5801579),
                listOf(-46.3898884, -23.5801579),
                listOf(-46.3898884, -23.5801579),
                listOf(-46.39469061, -23.57992669),
                listOf(-46.3900739, -23.58096337),
                listOf(-46.39433788, -23.58125055),
                listOf(-46.389993, -23.58091009),
                listOf(-46.39444605, -23.57875883),
                listOf(-46.39115977, -23.57787727),
                listOf(-46.38975028, -23.58075028),
                listOf(-46.3911596, -23.5778268),
                listOf(-46.3911596, -23.5778268),
                listOf(-46.39080054, -23.58214134),
                listOf(-46.39180164, -23.5825552),
                listOf(-46.39180164, -23.5825552),
                listOf(-46.39203141, -23.58262739),
                listOf(-46.39431283, -23.57819383),
                listOf(-46.38944233, -23.58054574),
                listOf(-46.38944233, -23.58054574),
                listOf(-46.39170411, -23.57716538),
                listOf(-46.39076522, -23.58265949),
                listOf(-46.39576496, -23.5808519),
                listOf(-46.39576496, -23.5808519),
                listOf(-46.3930215, -23.58333459),
                listOf(-46.393155, -23.5834788),
                listOf(-46.3940502, -23.58321129),
                listOf(-46.3921911, -23.5759757),
                listOf(-46.39218428, -23.57588406),
                listOf(-46.39218428, -23.57588406),
                listOf(-46.39218428, -23.57588406),
                listOf(-46.39218428, -23.57588406),
                listOf(-46.39218428, -23.57588406),
                listOf(-46.39218428, -23.57588406),
                listOf(-46.39296088, -23.58439029),
                listOf(-46.39064509, -23.58418332)
            )
        )

        return Response.success(ocorrenciasRaioResponse)
    }
}