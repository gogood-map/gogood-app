package com.gogood.mobile.home.domain.usecases

import com.gogood.mobile.home.data.repository.IMapRepository
import com.gogood.mobile.home.domain.models.LatLngOcorrencia
import com.google.android.gms.maps.model.LatLng
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class ObterCoordenadasOcorrenciasRaioUseCase(
    private val mapRepository: IMapRepository
): IObterCoordenadasOcorrenciaRaioUseCase {
    override suspend fun invoke(latLng: LatLng, raio: Double): Result<List<LatLng>> {
        try {
            val resposta = mapRepository.obterOcorrenciasRaio(latLng.latitude, latLng.longitude, raio)
            if (resposta.isSuccessful) {
               if(resposta.body()!!.isNotEmpty()){
                  return Result.success(resposta.body()!!)
               }else{
                   return Result.success(emptyList())
               }

            } else {
                return Result.failure(Exception("Não foi possível obter as coordenadas de ocorrências."))
            }
        }catch (e: ConnectException){
            return Result.failure(Exception("Não foi possível conectar-se a API."))
        }catch (t: SocketTimeoutException){
            return Result.failure(Exception("Tempo de consulta excedido."))
        }catch (h: UnknownHostException){
            return Result.failure(Exception("API fora do ar."))
        }
    }
}
