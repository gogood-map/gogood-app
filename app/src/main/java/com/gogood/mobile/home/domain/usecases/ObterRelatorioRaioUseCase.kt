package com.gogood.mobile.home.domain.usecases

import com.gogood.mobile.home.data.repository.IMapRepository
import com.gogood.mobile.home.data.repository.remote.MapRepository
import com.gogood.mobile.home.domain.models.RelatorioOcorrenciasResponse
import com.gogood.mobile.home.presentation.stateholders.MainStateHolder
import com.google.android.gms.maps.model.LatLng
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class ObterRelatorioRaioUseCase(
    private val mapRepository: IMapRepository
): IObterRelatorioRaioUseCase {

    override suspend fun invoke(
        latLng: LatLng,
        raio: Double
    ): Result<RelatorioOcorrenciasResponse> {
        try {
            val resposta = mapRepository.buscarRelatorioRaio(latLng.latitude, latLng.longitude, raio)
            if(resposta.isSuccessful){
                resposta.body()?.let {
                    return Result.success(resposta.body()!!)
                }
            }else{
                return Result.failure(Exception("Não foi possível conectar-se a API."))
            }
        }catch (e: ConnectException) {
            return Result.failure(Exception("Não foi possível conectar-se a API."))
        }catch (t: SocketTimeoutException){
            return Result.failure(Exception("Tempo de consulta excedido."))
        }
        catch (h: UnknownHostException){
            return Result.failure(Exception("API fora do ar."))
        }
        return Result.failure(Exception("Não foi possível conectar-se a API."))
    }
}